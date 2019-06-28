const { db, Cities, Countries, BookingCities, Coworks } = require('../db')
const util = require('util')
const Sequelize = require('sequelize')
const axios = require('axios')
const coworks = require('./results')


async function insert() {
  await Coworks.sync({})


  for (let cw of coworks) {
    if ((cw.work_space_id - 3060) < 0) continue

    const cwx = await Coworks.findOne({
      where: { id: cw.work_space_id }
    })
    if (cwx) {
      console.log(cwx.id, 'exists')
      continue
    }
    const city = await Cities.findOne({
      where: { name: cw.city }
    })
    if (!city) continue

    const addr = encodeURIComponent(`${cw.name}, ${cw.city}`)

    let geocode
    try {
      geocode  = await axios.get(
        `https://maps.googleapis.com/maps/api/geocode/json?address=${addr}&key=AIzaSyD8iy4-6ZMmZtKIFD00rigkEs_Bu4EKZ_Q`
      )
    } catch (e) {
      console.error(e)
      continue
    }

    if (geocode.data == null) continue
    if (geocode.data.results == null) continue
    if (geocode.data.results[ 0 ] == null) continue
    if (geocode.data.results[ 0 ].geometry == null) continue
    if (geocode.data.results[ 0 ].geometry.location == null) continue

    const c = await Coworks.create({
      id: cw.work_space_id,
      city_id: city.id,
      name: cw.name,
      m_price: cw.m_price,
      d_price: cw.d_price,
      currency: cw.currency,
      rating: cw.o_rate,
      latitude: geocode.data.results[ 0 ].geometry.location.lat,
      longitude: geocode.data.results[ 0 ].geometry.location.lng,
      image_url: 'https://coworker.imgix.net/photos/' + cw.coworkspace_url + cw.main_image
    })

    console.log(c.dataValues)
  }

}

insert()