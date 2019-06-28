package _map

import (
	"github.com/NomadicBleisures/Server/models/hotel"
	"github.com/NomadicBleisures/Server/models/coworking"
	"net/http"
	"github.com/go-chi/render"
	"github.com/NomadicBleisures/Server/controllers"
	"github.com/NomadicBleisures/Server/models/map"
	"strconv"
	"strings"
	"fmt"
	"github.com/sirupsen/logrus"
	"github.com/NomadicBleisures/Server/manager/booking"
)

func Get(w http.ResponseWriter, r *http.Request) {
	queryValues := r.URL.Query()
	latStr := queryValues.Get("lat")
	lngStr := queryValues.Get("lng")
	radiusStr := queryValues.Get("radius")
	if latStr == "" || lngStr == "" {
		latStr = "0"
		lngStr = "0"
	}
	lat, err := strconv.ParseFloat(latStr, 64);
	lng, err := strconv.ParseFloat(lngStr, 64);
	radius, err := strconv.ParseFloat(radiusStr, 64)
	_, cityID := _map.Get(lat, lng)
	logrus.Info(cityID)
	bookingCities := _map.GetBookingCityIDs(cityID)
	logrus.Info("BC: ", bookingCities)
	//bookingCitiesStr := strings.Trim(strings.Join(strings.Fields(fmt.Sprint(bookingCities)), ","), "[]")
	logrus.Info("HOT:", lat, lng, radius)
	hotelIDs := GetHotels(lat, lng, radius)
	logrus.Info("HOT:", hotelIDs)
	hotelsStr := strings.Trim(strings.Join(strings.Fields(fmt.Sprint(hotelIDs)), ","), "[]")
	logrus.Info(hotelsStr)
	hotelsData, err := hotel.GetHotelsWithID(hotelsStr, "")

	coworkingData, err := coworking.Get(fmt.Sprintf("%s", cityID))
	mapSearch := _map.MapSearch{
		Hotels:    hotelsData,
		Coworking: coworkingData,
	}
	if err != nil {
		controllers.ServeJsonError(w, r, "Something went wrong")
		return
	}
	render.JSON(w, r, mapSearch)
	return
}

func GetHotels(lat float64, lng float64, radius float64) ([]uint) {
	url := fmt.Sprintf("https://distribution-xml.booking.com/2.0/json/hotelAvailability?latitude=%f&longitude=%f&radius=%f&checkin=2019-09-01&checkout=2019-09-05&room1=A&rows=10", lat, lng, radius)
	logrus.Info(url)
	request := booking.Request{
		//Url:    fmt.Sprintf("https://distribution-xml.booking.com/2.0/json/hotelAvailability?latitude=%f&longitude=%f&radius=%d&checkin=2019-09-01&checkout=2019-09-02&room1=A&rows=10", lat, lng, radius),
		Url:    url,
		Method: "GET",
	}
	var hotelsArr []map[string]interface{}
	hotelsArr, err := booking.MakeRequest(request)
	if err != nil {
		logrus.Error(err)
	}

	var hotelIDs []uint
	for _, obj := range hotelsArr {
		id := obj["hotel_id"].(float64)
		hotelIDs = append(hotelIDs, uint(id))
	}
	return hotelIDs
}
