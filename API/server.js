const express = require('express')
const { Cities, BookingCities, Countries } = require('./db')
const { Op } = require('sequelize')
const Sequelize = require('sequelize')

const app = express()

app.get('/cities', async (req, res) => {
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

app.listen(4242, () => {
  console.log('Server started on http://localhost:4242')
})