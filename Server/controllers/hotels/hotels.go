package hotels

import (
	"github.com/NomadicBleisures/Server/controllers"
	"github.com/NomadicBleisures/Server/models/hotel"
	"github.com/go-chi/render"
	"net/http"
)

func Get(w http.ResponseWriter, r *http.Request) {
	queryValues := r.URL.Query()
	cityIDStr := queryValues.Get("city_ids")
	extras := queryValues.Get("extras")
	hotelDetails, err := hotel.Get(cityIDStr, extras)
	if err != nil {
		controllers.ServeJsonError(w, r, "Something went wrong")
		return
	}
	render.JSON(w, r, hotelDetails)
}
