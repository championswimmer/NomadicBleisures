package coworking

import (
	"github.com/NomadicBleisures/Server/models/coworking"
	"github.com/go-chi/render"
	"net/http"
)

func Get(w http.ResponseWriter, r *http.Request) {
	queryValues := r.URL.Query()
	cityIDStr := queryValues.Get("city_ids")
	var coworkingDetails interface{}
	if cityIDStr == "-2092174" {
		coworkingDetails, _ = coworking.GetMumbai()
	}
	if cityIDStr == "-2676772" {
		coworkingDetails, _ = coworking.GetMumbai()
	}

	render.JSON(w, r, coworkingDetails)
}
