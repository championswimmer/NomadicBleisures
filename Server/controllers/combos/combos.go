package combos

import (
	"net/http"

	"github.com/NomadicBleisures/Server/models/combos"
	"github.com/go-chi/render"
)

func Get(w http.ResponseWriter, r *http.Request) {
	queryValues := r.URL.Query()
	cityIDStr := queryValues.Get("city_ids")
	combosData, _ := combos.Get(cityIDStr)
	render.JSON(w, r, combosData)
}
