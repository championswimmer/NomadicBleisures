package coworking

import "github.com/NomadicBleisures/Server/manager/coworker"

func GetMumbai() (interface{}, error) {
	photoURL := "https://coworker.imgix.net/photos/"
	coworkingPlaces := coworker.CoworkingPlacesMumbai
	for _, c := range coworkingPlaces {
		c["image"] = photoURL + c["coworkspace_url"] + c["main_image"]
	}
	return coworkingPlaces, nil
}

func GetBali() (hotels interface{}, err error) {
	return coworker.CoworkingPlacesMumbai, nil
}
