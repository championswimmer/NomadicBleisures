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
)

func Get(w http.ResponseWriter, r *http.Request) {
	queryValues := r.URL.Query()
	latStr := queryValues.Get("lat")
	lngStr := queryValues.Get("lng")
	if latStr == "" || lngStr == "" {
		latStr = "0"
		lngStr = "0"
	}
	lat, err := strconv.ParseFloat(latStr, 64);
	lng, err := strconv.ParseFloat(lngStr, 64);
	_, cityID := _map.Get(lat, lng)
	logrus.Info(cityID)
	bookingCities := _map.GetBookingCityIDs(cityID)
	logrus.Info("BC: ", bookingCities)
	bookingCitiesStr := strings.Trim(strings.Join(strings.Fields(fmt.Sprint(bookingCities)), ","), "[]")
	hotelsData, err := hotel.Get(bookingCitiesStr, "")
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
