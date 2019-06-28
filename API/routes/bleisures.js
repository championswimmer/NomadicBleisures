const Sequelize = require('sequelize')
const { Op } = Sequelize
const { Cities, BookingCities, Countries } = require('../db')
const { Router } = require('express')


const route = Router()
route.get('/', async (req, res) => {
  const whereArr = []

  // Selection args
  if (req.query.cost) {
    let costParam

    switch (req.query.cost) {
      case 'high': {
        costParam = { [ Op.gt ]: 1500 }
        break
      }
      case 'medium': {
        costParam = {
          [ Op.and ]: {
            [ Op.gte ]: 750,
            [ Op.lte ]: 1500
          }
        }
        break
      }
      case 'low': {
        costParam = { [ Op.lt ]: 750 }
        break
      }
    }

    whereArr.push(
      { livingCost: costParam }
    )
  }

  if (req.query.weather) {
    let weatherParam

    switch (req.query.weather) {
      case 'cold': {
        weatherParam = { [ Op.lt ]: 20 }
        break
      }
      case 'mild': {
        weatherParam = {
          [ Op.and ]: {
            [ Op.gte ]: 20,
            [ Op.lte ]: 30
          }
        }
        break
      }
      case 'warm': {
        weatherParam = { [ Op.gt ]: 30 }
        break
      }
    }
    whereArr.push(
      { tempC: weatherParam }
    )
  }

  if (req.query.low_pop) {
    whereArr.push({
      population: { [ Op.lt ]: 30000 }
    })
  }

  if (req.query.aqi) {
    whereArr.push({
      aqi: { [ Op.lt ]: 50 }
    })
  }

  if (req.query.internet) {
    whereArr.push({
      internetMbps: { [ Op.gt ]: 20 }
    })
  }

  if (req.query.high_nomad) {
    whereArr.push({
      userBeen: { [ Op.gt ]: 500 }
    })
  }


  const cities = await Cities.findAll({
    include: [ BookingCities, Countries ],
    where: {
      [ Op.and ]: whereArr
    },
    order: [
      [ 'score', 'DESC' ]
    ],
    limit: req.query.limit != null ? +(req.query.limit) : 8
  })

  // Strip out cities with no Booking.com ids
  const bookingEnabledCities = cities.filter(c => (c.booking_cities.length > 0))

  res.send(bookingEnabledCities)
})

module.exports = { route }