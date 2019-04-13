package router

import (
	"github.com/NomadicBleisures/Server/controllers/hotels"
	"github.com/go-chi/chi"
	"github.com/go-chi/chi/middleware"
)

func HotelsRoutes(r chi.Router) {
	r.Get("/", hotels.Get)

}
func GetRouter() (r *chi.Mux, err error) {
	r = chi.NewRouter()
	r.Use(middleware.Logger)

	r.Group(func(r chi.Router) {
		r.Route("/api/v1", func(r chi.Router) {
			r.Route("/hotels", HotelsRoutes)
		})
	})

	return
}