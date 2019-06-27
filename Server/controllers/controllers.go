package controllers

import (
	"net/http"

	"github.com/go-chi/render"
	"github.com/sirupsen/logrus"
)

func ServeJsonError(w http.ResponseWriter, r *http.Request, error string) {
	logrus.Errorf("%s: %s", r.RequestURI, error)
	render.JSON(w, r, struct {
		Error string `json:"error"`
	}{Error: error})
}

func ServeJsonSuccess(w http.ResponseWriter, r *http.Request, success string) {
	render.JSON(w, r, struct {
		Success string `json:"success"`
	}{Success: success})
}
