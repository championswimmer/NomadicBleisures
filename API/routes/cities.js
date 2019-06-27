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

  res.send(cities)
})

module.exports = { route }