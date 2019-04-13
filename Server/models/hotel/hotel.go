package hotel

import (
	"fmt"
	"github.com/NomadicBleisures/Server/manager/booking"
)

func Get(cityID string, extras string) (hotels interface{}, err error) {

	request := booking.Request{
		Url:    fmt.Sprintf("https://distribution-xml.booking.com/2.0/json/hotels?"+"city_ids=%s&extras=%s&rows=10", cityID, extras),
		Method: "GET",
	}
	hotels, _ = booking.MakeRequest(request)
	return
}
