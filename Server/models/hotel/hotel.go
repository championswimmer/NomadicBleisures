package hotel

import (
	"fmt"
	"github.com/NomadicBleisures/Server/manager/booking"
	"encoding/json"
	"github.com/sirupsen/logrus"
)

type HotelData struct {
	Name                 string                 `json:"name,omitempty"`
	Image                string                 `json:"image,omitempty"`
	Rating               float64                `json:"rating,omitempty"`
	NumCoworking         int                    `json:"num_coworking,omitempty"`
	Price                float64                `json:"price,omitempty"`
	Currency             string                 `json:"currency,omitempty"`
	DeepLink             string                 `json:"deeplink,omitempty"`
	RecommendedCoworking map[string]interface{} `json:"recommended_coworking,omitempty"`
	Loc                  Location               `json:"locaiton,omitempty"`
}

type Location struct {
	Lat float64 `json:"latitude,omitempty"`
	Lng float64 `json:"longitude,omitempty"`
}

func Get(cityID string, extras string) ([]HotelData, error) {
	extras = "hotel_description,hotel_photos,hotel_info,room_info"
	request := booking.Request{
		Url:    fmt.Sprintf("https://distribution-xml.booking.com/2.0/json/hotels?"+"city_ids=%s&extras=%s&rows=10", cityID, extras),
		Method: "GET",
	}
	var hotelsArr []HotelData
	hotelsData, _ := booking.MakeRequest(request)

	for i, hotel := range hotelsData {
		h := HotelData{}
		hotelData := hotel["hotel_data"].(map[string]interface{})
		h.Name = hotelData["name"].(string)
		hotelPhotos := hotelData["hotel_photos"].([]interface{})
		hotelImage := hotelPhotos[0].(map[string]interface{})
		h.Image = hotelImage["url_original"].(string)
		h.Rating = hotelData["review_score"].(float64)
		h.DeepLink = hotelData["deep_link_url"].(string)
		h.Currency = hotelData["currency"].(string)
		h.NumCoworking = (len(hotelsData) - i + 1) * 5 / 3
		var loc Location

		b, err := json.Marshal(hotelData["location"])
		if err != nil {
			logrus.Error(err)
			return nil, err
		}
		json.Unmarshal([]byte(string(b)), &loc)
		h.Loc = loc
		logrus.Info(h.Loc)
		roomData := hotel["room_data"].([]interface{})
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

		//if radius != 0 {
		//distance := utils.Distance()
		//} else {
		hotelsArr = append(hotelsArr, h)
		//}
	}

	return hotelsArr, nil
}

func GetHotelsWithID(hotelIDs string, extras string) ([]HotelData, error) {
	extras = "hotel_description,hotel_photos,hotel_info,room_info"
	request := booking.Request{
		Url:    fmt.Sprintf("https://distribution-xml.booking.com/2.0/json/hotels?"+"hotel_ids=%s&extras=%s&rows=10", hotelIDs, extras),
		Method: "GET",
	}
	var hotelsArr []HotelData
	hotelsData, _ := booking.MakeRequest(request)

	for i, hotel := range hotelsData {
		h := HotelData{}
		hotelData := hotel["hotel_data"].(map[string]interface{})
		h.Name = hotelData["name"].(string)
		hotelPhotos := hotelData["hotel_photos"].([]interface{})
		hotelImage := hotelPhotos[0].(map[string]interface{})
		h.Image = hotelImage["url_original"].(string)
		h.Rating = hotelData["review_score"].(float64)
		h.DeepLink = hotelData["deep_link_url"].(string)
		h.Currency = hotelData["currency"].(string)
		h.NumCoworking = (len(hotelsData) - i + 1) * 5 / 3
		var loc Location

		b, err := json.Marshal(hotelData["location"])
		if err != nil {
			logrus.Error(err)
			return nil, err
		}
		json.Unmarshal([]byte(string(b)), &loc)
		h.Loc = loc
		logrus.Info(h.Loc)
		roomData := hotel["room_data"].([]interface{})
		for _, room := range roomData {
			r := room.(map[string]interface{})
			roomInfo := r["room_info"].(map[string]interface{})
			if roomInfo["min_price"] != 0 && h.Price == 0 {
				h.Price = roomInfo["min_price"].(float64)
			}
		}
		//if cityID == "-2092174" {
		//	h.RecommendedCoworking = map[string]interface{}{
		//		"city":            "Mumbai",
		//		"country":         "India",
		//		"coworkspace_url": "india/mumbai/of10",
		//		"currency":        "INR",
		//		"d_price":         "850.00",
		//		"image":           "https://coworker.imgix.net/photos/india/mumbai/of10/main-1490781564.jpg",
		//		"m_price":         "10500.00",
		//		"main_image":      "/main-1490781564.jpg",
		//		"name":            "Of10",
		//		"num_hotels":      "9",
		//		"rating":          "9.8",
		//	}
		//} else {
		//	h.RecommendedCoworking = map[string]interface{}{
		//		"city":            "Denpasar",
		//		"country":         "Indonesia",
		//		"coworkspace_url": "indonesia/denpasar/district-canggu",
		//		"currency":        "IDR",
		//		"d_price":         "110000.00",
		//		"image":           "https://coworker.imgix.net/photos/indonesia/denpasar/district-canggu/main.JPG",
		//		"m_price":         "1650000.00",
		//		"main_image":      "/main.JPG",
		//		"name":            "district canggu",
		//		"rating":          "9.4",
		//	}
		//}

		//if radius != 0 {
		//	distance := utils.Distance()
		//} else {
			hotelsArr = append(hotelsArr, h)
		//}
	}

	return hotelsArr, nil
}

func Sort(radius uint, lat float64, lng float64) {

}

func Book() {

}
