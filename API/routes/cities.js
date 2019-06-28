const Sequelize = require('sequelize')
const { Op } = Sequelize
const { Cities, BookingCities, Countries } = require('../db')
const { Router } = require('express')


const route = Router()
route.get('/', async (req, res) => {
  const whereArr = []

  // Query by name
  if (req.query.name) {
    whereArr.push(
      Sequelize.where(
        Sequelize.fn('lower', Sequelize.col('city.name')),
        { [ Op.like ]: `%${req.query.name}%` }
      )
    )
  }

  const cities = await Cities.findAll({
    include: [ BookingCities, Countries ],
    where: {
      [ Op.and ]: whereArr
    },
    order: [
      [ 'score', 'DESC' ]
    ],
    limit: req.query.limit != null ? +(req.query.limit) : 5
  })

  // Cities that start with are more preferred
  if (req.query.name) {
    cities.sort((c1, c2) =>
      c1.name.toLowerCase().indexOf(req.query.name) - c2.name.toLowerCase().indexOf(req.query.name)
    )
  }

  // Strip out cities with no Booking.com ids
  const bookingEnabledCities = cities.filter(c => (c.booking_cities.length > 0))

  res.send(bookingEnabledCities)
})

module.exports = { route }