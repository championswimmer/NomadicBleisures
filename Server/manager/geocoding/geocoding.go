package geocoding

import (
	"github.com/codingsince1985/geo-golang/google"
	"github.com/codingsince1985/geo-golang"
	"github.com/sirupsen/logrus"
)

var (
	geocoder geo.Geocoder
)

func Initialize() {
	geocoder = google.Geocoder("AIzaSyA4f7clsOdEZZALFqA4Pe6DnlniUOuEeJY")
}

func FetchCity(lat float64, lng float64) string {
	logrus.Info(geocoder)
	address, err := geocoder.ReverseGeocode(lat, lng)
	if err != nil {
		logrus.Error(err)
	}
	if address != nil {
		logrus.Infof("Address of (%.6f,%.6f) is %s\n", lat, lng, address.FormattedAddress)
	} else {
		logrus.Info("got <nil> address")
	}
	if address != nil {
		return address.City
	}
	return "Amsterdam"
}
