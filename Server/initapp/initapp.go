package initapp

import (
	"github.com/NomadicBleisures/Server/manager/coworker"
	"github.com/NomadicBleisures/Server/manager/mysql"
	"github.com/NomadicBleisures/Server/manager/geocoding"
)

func Init() {
	coworker.InitData()
	mysql.Initialize()
	geocoding.Initialize()
}
