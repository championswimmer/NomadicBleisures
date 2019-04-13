package booking

import (
	"encoding/json"
	"io/ioutil"
	"net/http"
	"time"
)

var (
	client *http.Client
)

type Request struct {
	Payload map[string]interface{}
	Method  string
	Url     string
}

func GetClient() *http.Client {
	if client == nil {
		client = &http.Client{
			Timeout: time.Second * 10,
		}
	}
	return client
}

func MakeRequest(request Request) (response interface{}, err error) {
	client := GetClient()
	if err != nil {
		return response, err
	}
	url := request.Url
	req, _ := http.NewRequest(request.Method, url, nil)
	req.Header.Add("Content-Type", "application/json")
	req.Header.Add("Authorization", "Basic aGFja2F0b25fdGVhbV9wcmVtcGFsOlRzekNXU0dnWk5FdDZ0Sg==")
	resp, err := client.Do(req)
	if err != nil {
		return
	}
	defer resp.Body.Close()
	body, _ := ioutil.ReadAll(resp.Body)
	if resp.StatusCode != 200 {

	}
	json.Unmarshal(body, &response)
	return
}
