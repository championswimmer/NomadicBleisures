package coworking

import (
	"github.com/NomadicBleisures/Server/manager/coworker"
	"github.com/NomadicBleisures/Server/models/hotel"
)

type Coworking struct {
	Country           string
	City              string
	Name              string
	Curr              string
	MPrice            int
	DPrice            int
	Rating            float32
	Url               string
	Image             string
	RecommendedHotels []hotel.HotelData
}

func Get(cityID string) ([]map[string]interface{}, error) {
	photoURL := "https://coworker.imgix.net/photos/"
	var coworkingPlaces []map[string]interface{}
	if cityID == "-2676772" {
		coworkingPlaces = coworker.CoworkingPlacesDenpasar
	} else {
		coworkingPlaces = coworker.CoworkingPlacesMumbai
	}
	for _, c := range coworkingPlaces {
		c["image"] = photoURL + c["coworkspace_url"].(string) + c["main_image"].(string)
	}
	return coworkingPlaces, nil
}

func Book() {

}
