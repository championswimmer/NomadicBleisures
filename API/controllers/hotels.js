const axios = require('axios')

async function getHotelsByBookingCityId(cityId) {
  try {
    const hotels = await axios.get(`http://178.128.249.124:8080/api/v1/hotels?city_ids=${cityId}`)
    return hotels
  } catch (e) {
    return []
  }
}

module.exports = {
  getHotelsByBookingCityId
}