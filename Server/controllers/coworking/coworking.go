package coworking

import (
	"net/http"
	"time"

	"github.com/NomadicBleisures/Server/models/coworking"
	"github.com/go-chi/render"
)

func Get(w http.ResponseWriter, r *http.Request) {
	queryValues := r.URL.Query()
	cityIDStr := queryValues.Get("city_ids")
	var coworkingDetails interface{}
	coworkingDetails, _ = coworking.Get(cityIDStr)
	time.Sleep(time.Millisecond * 500)
	render.JSON(w, r, coworkingDetails)
}
