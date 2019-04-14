package coworker

var CoworkingPlacesMumbai [7]map[string]string

func getData() {

	CoworkingPlacesMumbai[0] = make(map[string]string)

	CoworkingPlacesMumbai[0]["country"] = "India"
	CoworkingPlacesMumbai[0]["city"] = "Mumbai"
	CoworkingPlacesMumbai[0]["name"] = "Social Offline - Colaba Mumbai"
	CoworkingPlacesMumbai[0]["currency"] = "INR"
	CoworkingPlacesMumbai[0]["m_price"] = "5000.00"
	CoworkingPlacesMumbai[0]["num_hotels"] = "5"
	CoworkingPlacesMumbai[0]["rating"] = "3.7"
	CoworkingPlacesMumbai[0]["coworkspace_url"] = "india/mumbai/social-offline-colaba-mumbai"
	CoworkingPlacesMumbai[0]["main_image"] = "/main.jpg"

	CoworkingPlacesMumbai[1] = make(map[string]string)
	CoworkingPlacesMumbai[1]["country"] = "India"
	CoworkingPlacesMumbai[1]["city"] = "Mumbai"
	CoworkingPlacesMumbai[1]["name"] = "Social Offline - Lower Parel"
	CoworkingPlacesMumbai[1]["currency"] = "INR"
	CoworkingPlacesMumbai[1]["m_price"] = "5000.00"
	CoworkingPlacesMumbai[1]["num_hotels"] = "13"
	CoworkingPlacesMumbai[1]["rating"] = "4.3"
	CoworkingPlacesMumbai[1]["coworkspace_url"] = "india/mumbai/social-offline-lower-parel"
	CoworkingPlacesMumbai[1]["main_image"] = "/main.jpg"

	CoworkingPlacesMumbai[2] = make(map[string]string)
	CoworkingPlacesMumbai[2]["country"] = "India"
	CoworkingPlacesMumbai[2]["city"] = "Navi Mumbai"
	CoworkingPlacesMumbai[2]["name"] = "91springboard Navi Mumbai"
	CoworkingPlacesMumbai[2]["currency"] = "INR"
	CoworkingPlacesMumbai[2]["d_price"] = "549.00"
	CoworkingPlacesMumbai[2]["m_price"] = "5999.00"
	CoworkingPlacesMumbai[2]["num_hotels"] = "3"
	CoworkingPlacesMumbai[2]["rating"] = "4.0"
	CoworkingPlacesMumbai[2]["coworkspace_url"] = "india/navi-mumbai/91springboard-navi-mumbai"
	CoworkingPlacesMumbai[2]["main_image"] = "/main.jpg"

	CoworkingPlacesMumbai[3] = make(map[string]string)
	CoworkingPlacesMumbai[3]["country"] = "India"
	CoworkingPlacesMumbai[3]["city"] = "Mumbai"
	CoworkingPlacesMumbai[3]["name"] = "Ministry Of New"
	CoworkingPlacesMumbai[3]["currency"] = "INR"
	CoworkingPlacesMumbai[3]["d_price"] = "1200.00"
	CoworkingPlacesMumbai[3]["m_price"] = "19500.00"
	CoworkingPlacesMumbai[3]["num_hotels"] = "14"
	CoworkingPlacesMumbai[3]["rating"] = "4.5"
	CoworkingPlacesMumbai[3]["coworkspace_url"] = "india/mumbai/ministry-of-new"
	CoworkingPlacesMumbai[3]["main_image"] = "/main-1498454947.jpg"

	CoworkingPlacesMumbai[4] = make(map[string]string)
	CoworkingPlacesMumbai[4]["country"] = "India"
	CoworkingPlacesMumbai[4]["city"] = "Mumbai"
	CoworkingPlacesMumbai[4]["name"] = "The Playce"
	CoworkingPlacesMumbai[4]["currency"] = "INR"
	CoworkingPlacesMumbai[4]["x_plan"] = "FREE"
	CoworkingPlacesMumbai[4]["d_price"] = "499.00"
	CoworkingPlacesMumbai[4]["m_price"] = "6500.00"
	CoworkingPlacesMumbai[4]["num_hotels"] = "6"
	CoworkingPlacesMumbai[4]["rating"] = "3.9"
	CoworkingPlacesMumbai[4]["coworkspace_url"] = "india/mumbai/the-playce"
	CoworkingPlacesMumbai[4]["main_image"] = "/main.jpg"

	CoworkingPlacesMumbai[5] = make(map[string]string)
	CoworkingPlacesMumbai[5]["country"] = "India"
	CoworkingPlacesMumbai[5]["city"] = "Mumbai"
	CoworkingPlacesMumbai[5]["name"] = "Of10"
	CoworkingPlacesMumbai[5]["currency"] = "INR"
	CoworkingPlacesMumbai[5]["coworkspace_url"] = "india/mumbai/of10"
	CoworkingPlacesMumbai[5]["main_image"] = "/main-1490781564.jpg"
	CoworkingPlacesMumbai[5]["x_plan"] = "FREE"
	CoworkingPlacesMumbai[5]["d_price"] = "850.00"
	CoworkingPlacesMumbai[5]["m_price"] = "10500.00"
	CoworkingPlacesMumbai[5]["num_hotels"] = "9"
	CoworkingPlacesMumbai[5]["rating"] = "4.9"

	CoworkingPlacesMumbai[6] = make(map[string]string)
	CoworkingPlacesMumbai[6]["country"] = "India"
	CoworkingPlacesMumbai[6]["city"] = "Mumbai"
	CoworkingPlacesMumbai[6]["name"] = "G-CORP MEDIA"
	CoworkingPlacesMumbai[6]["currency"] = "INR"
	CoworkingPlacesMumbai[6]["coworkspace_url"] = "india/mumbai/g-corp-media"
	CoworkingPlacesMumbai[6]["main_image"] = "/main.jpg"
	CoworkingPlacesMumbai[6]["x_plan"] = "FREE"
	CoworkingPlacesMumbai[6]["d_price"] = "4000.00"
	CoworkingPlacesMumbai[6]["m_price"] = "65000.00"
	CoworkingPlacesMumbai[6]["num_hotels"] = "15"
	CoworkingPlacesMumbai[6]["rating"] = "4.6"

	return
}

func InitData() {
	getData()
}
