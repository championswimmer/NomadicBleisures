const { db, Cities, Countries, BookingCities, Coworks } = require('../db')
const util = require('util')
const Sequelize = require('sequelize')
const axios = require('axios')


async function getCoworksOfCity(cityId, limit = true) {
  const coworks = await Coworks.findAll({
    where: {
      city_id: cityId
    },
    order: [
      [ 'rating', 'DESC' ]
    ],
    limit: limit ? 3 : 100
  })

  return coworks
}

module.exports = {
  getCoworksOfCity
}