const Sequelize = require('sequelize')
const { Op } = Sequelize
const { Cities, BookingCities, Countries } = require('../db')
const { Router } = require('express')

const route = Router()

route.get('/selections', async (req, res) => {

  res.json({
    selections: [
      { type: 'cost',
        title: 'Cost of Living',
        options: [
          {value: 'low', title: '< $25 / d'},
          {value: 'medium', title: '$25 ~ $50 / d'},
          {value: 'high', title: '> $50 / d'}
        ]
      },
      {
        type: 'weather',
        title: 'Weather',
        options: [
          {value: 'cold', title: '❄️ Cold'},
          {value: 'warm', title: '☀️ Warm'},
          {value: 'mild', title: '🌥 Mild'}
        ]
      },
      {
        type: 'timezone',
        title: 'Near timezone',
        options: [
          {value: '-0700', title: '-0700 (San Francisco)'},
          {value: '-0400', title: '-0400 (New York)'},
          {value: '0000', title: '0000 (London)'},
          {value: '0800', title: '0800 (Singapore)'}
        ]
      }
    ],
    options: [
      {type: 'aqi', title: 'Clean Air 🍃'},
      {type: 'internet', title: 'Fast Internet 🔌'}
    ]
  })

})

module.exports = { route }