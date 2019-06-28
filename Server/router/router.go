package router

import (
	"github.com/NomadicBleisures/Server/controllers/combos"
	"github.com/NomadicBleisures/Server/controllers/coworking"
	"github.com/NomadicBleisures/Server/controllers/hotels"
	"github.com/go-chi/chi"
	"github.com/go-chi/chi/middleware"
	"github.com/NomadicBleisures/Server/controllers/map"
)

func HotelsRoutes(r chi.Router) {
	r.Get("/", hotels.Get)

}

func CoworkingPlacesRoutes(r chi.Router) {
	r.Get("/", coworking.Get)
}

func ComboRoutes(r chi.Router) {
	r.Get("/", combos.Get)
}

func MapSearchRoutes(r chi.Router) {
	r.Get("/", _map.Get)
}

func GetRouter() (r *chi.Mux, err error) {
	r = chi.NewRouter()
	r.Use(middleware.Logger)

	r.Group(func(r chi.Router) {
		r.Route("/api/v1", func(r chi.Router) {
			r.Route("/hotels", HotelsRoutes)
			r.Route("/coworking-places", CoworkingPlacesRoutes)
			r.Route("/combos", ComboRoutes)
			r.Route("/map-search", MapSearchRoutes)
		})
	})

	return
}
