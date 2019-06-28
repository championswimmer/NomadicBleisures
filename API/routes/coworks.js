const Sequelize = require('sequelize')
const { Op } = Sequelize
const { Cities, BookingCities, Countries, Coworks } = require('../db')
const { Router } = require('express')
const { getCoworksOfCity } = require('../controllers/coworks')

const route = Router()

route.get('/', async (req, res) => {

  const bookingCityId = req.query.city_id
  if (!bookingCityId) return res.status(400).send({ message: 'No cityid' })

  const bc = await BookingCities.findOne({
    include: [ Cities ],
    where: { id: bookingCityId }
  })
  if (bc == null || bc.city == null) return res.status(400).send({ message: 'No city found' })


  const cityId = bc.city.id

  const coworks = await getCoworksOfCity(cityId, false)

  res.send(coworks)


})

module.exports = {
  route
}