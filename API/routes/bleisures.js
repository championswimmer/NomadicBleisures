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
            [ Op.gt ]: 750,
            [ Op.lt ]: 1500
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