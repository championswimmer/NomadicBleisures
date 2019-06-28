package mysql

import (
	"database/sql"
	_ "github.com/go-sql-driver/mysql"
	"github.com/jinzhu/gorm"
	_ "github.com/jinzhu/gorm/dialects/mysql"
	"log"
)

var (
	db   *gorm.DB
	conn *sql.DB
)

func Initialize() {
	mysqlUser := "nomad"
	mysqlPassword := "champselyses"
	mysqlHostname := "178.128.249.124"
	mysqlPort := "3306"
	mysqlDatabase := "nomadb"
	var err error
	db, err = gorm.Open("mysql", mysqlUser+":"+mysqlPassword+"@tcp("+mysqlHostname+":"+mysqlPort+")/"+mysqlDatabase)
	if err != nil {
		log.Fatal(err)
	}

	conn = db.DB()
	err = conn.Ping()

	if err != nil {
		log.Fatal(err.Error())
	}
}

func GetDB() *sql.DB {
	return conn
}

func Get() *gorm.DB {
	return db
}
