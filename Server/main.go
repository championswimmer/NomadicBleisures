package main

import (
	"fmt"
	"github.com/NomadicBleisures/Server/initapp"
	"github.com/NomadicBleisures/Server/router"
	"github.com/sirupsen/logrus"
	"github.com/vrecan/death"
	"net/http"
	"syscall"
)

func main() {
	initapp.Init()

	r, err := router.GetRouter()
	if err != nil {
		logrus.Panic("STOP")
	}
	fmt.Print("Starting server on port 8080")
	err = http.ListenAndServe(":8080", r)

	death := death.NewDeath(syscall.SIGINT, syscall.SIGTERM)
	death.WaitForDeath()
}
