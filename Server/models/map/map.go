package _map

import (
	"github.com/NomadicBleisures/Server/models/hotel"
	"github.com/NomadicBleisures/Server/manager/geocoding"
	"github.com/NomadicBleisures/Server/manager/mysql"
	"github.com/sirupsen/logrus"
)

type MapSearch struct {
	Hotels []hotel.HotelData
	//Coworking   []coworking.Coworking
	Coworking   []map[string]interface{}
	Attractions map[string]interface{}
}

func Get(lat float64, lng float64) (cityName string, cityID uint) {
	cityName = geocoding.FetchCity(lat, lng);
	cityID = getCityID(cityName)
	return
}

func getCityID(cityName string) (cityID uint) {
	DB := mysql.GetDB()
	row := DB.QueryRow(`SELECT id FROM cities WHERE name LIKE ? LIMIT 1`, cityName)
	_ = row.Scan(&cityID)
	return
}

func GetBookingCityIDs(cityID uint) (cityIDs []int64) {
	DB := mysql.GetDB()
	rows, err := DB.Query(`SELECT id FROM booking_cities WHERE cityId = ?`, cityID)
	if err != nil {
		logrus.Error(err)
		return
	}

	defer rows.Close()
	for rows.Next() {
		var bCityID int64
		err = rows.Scan(&bCityID)
		if err != nil {
			logrus.Error(err)
			return
		}
		cityIDs = append(cityIDs, bCityID)
	}
	return

}
