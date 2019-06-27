const { db, Cities, Countries, BookingCities } = require('../db')
const util = require('util')
const Sequelize = require('sequelize')
const axios = require('axios')

const bookingApi = axios.create({
  auth: {
    username: 'hackaton_team_arnav',
    password: '2bin2bin'
  },
  baseURL: 'https://distribution-xml.booking.com/2.0/json'
})

async function task() {
  BookingCities.sync({ })

  const cities = await Cities.findAll({
    include: [ Countries ],
    limit: 200,
    offset: 600,
  })
  for (c of cities) {
    console.log(`${c.name}, ${c.country.name}`)
    const searchParam = `${c.name}, ${c.country.name}`
    try {
      encodeURIComponent()
      const resp = await bookingApi.get(`/autocomplete?language=en&text=${encodeURIComponent(searchParam)}`)
      await (util.promisify(setTimeout))(50)

      for (r of resp.data.result) {
        if (r.type == 'city') {
          try {
            const bc = await BookingCities.create({
              id: r.id,
              url: r.url,
              cityId: c.id,
              label: r.label
            })
            console.log(bc.dataValues)
          } catch (e) {
            console.error(r)
            console.error(e)
          }

        }
      }
    } catch (e) {
      console.error(e)
    }

  }
}

task()