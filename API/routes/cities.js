const Sequelize = require('sequelize')
const { Op } = Sequelize
const { Cities, BookingCities, Countries } = require('../db')
const { Router } = require('express')


const route = Router()
route.get('/', async (req, res) => {
  const whereArr = []

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
    }
  })

  if (req.query.name) {
    cities.sort((c1, c2) =>
      c1.name.toLowerCase().indexOf(req.query.name) - c2.name.toLowerCase().indexOf(req.query.name)
    )
  }

  res.send(cities)
})

module.exports = { route }