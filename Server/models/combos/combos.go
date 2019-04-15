package combos

import (
	"fmt"

	"github.com/NomadicBleisures/Server/manager/booking"
	"github.com/NomadicBleisures/Server/models/coworking"
	"github.com/NomadicBleisures/Server/models/hotel"
)

type Combo struct {
	Hotel     hotel.HotelData        `json:"hotel,omitempty"`
	Coworking map[string]interface{} `json:"coworking,omitempty"`
}

func Get(cityID string) ([]Combo, error) {
	extras := "hotel_description,hotel_photos,hotel_info,room_info"
	rows := 10
	coworkingPlaces, _ := coworking.Get(cityID)
	combos := []Combo{}
	if cityID == "-2676772" {
		rows = 3
	} else {
		rows = 7
	}
	request := booking.Request{
		Url:    fmt.Sprintf("https://distribution-xml.booking.com/2.0/json/hotels?"+"city_ids=%s&extras=%s&rows=%d", cityID, extras, rows),
		Method: "GET",
	}
	hotelsData, _ := booking.MakeRequest(request)
	for i, hotelItem := range hotelsData {
		h := hotel.HotelData{}
		hotelData := hotelItem["hotel_data"].(map[string]interface{})
		h.Name = hotelData["name"].(string)
		hotelPhotos := hotelData["hotel_photos"].([]interface{})
		hotelImage := hotelPhotos[0].(map[string]interface{})
		h.Image = hotelImage["url_original"].(string)
		h.Rating = hotelData["review_score"].(float64)
		h.DeepLink = hotelData["deep_link_url"].(string)
		h.Currency = hotelData["currency"].(string)
		h.NumCoworking = (len(hotelsData) - i + 1) * 5 / 3
		roomData := hotelItem["room_data"].([]interface{})
		for _, room := range roomData {
			r := room.(map[string]interface{})
			roomInfo := r["room_info"].(map[string]interface{})
			if roomInfo["min_price"] != 0 && h.Price == 0 {
				h.Price = roomInfo["min_price"].(float64)
			}
		}
		if cityID == "-2092174" {
			h.RecommendedCoworking = map[string]interface{}{
				"city":            "Mumbai",
				"country":         "India",
				"coworkspace_url": "india/mumbai/of10",
				"currency":        "INR",
				"d_price":         "850.00",
				"image":           "https://coworker.imgix.net/photos/india/mumbai/of10/main-1490781564.jpg",
				"m_price":         "10500.00",
				"main_image":      "/main-1490781564.jpg",
				"name":            "Of10",
				"num_hotels":      "9",
				"rating":          "9.8",
			}
		} else {
			h.RecommendedCoworking = map[string]interface{}{
				"city":            "Denpasar",
				"country":         "Indonesia",
				"coworkspace_url": "indonesia/denpasar/district-canggu",
				"currency":        "IDR",
				"d_price":         "110000.00",
				"image":           "https://coworker.imgix.net/photos/indonesia/denpasar/district-canggu/main.JPG",
				"m_price":         "1650000.00",
				"main_image":      "/main.JPG",
				"name":            "district canggu",
				"rating":          "9.4",
			}
		}
		combo := Combo{
			Hotel:     h,
			Coworking: coworkingPlaces[i],
		}
		combos = append(combos, combo)
	}

	return combos, nil
}
