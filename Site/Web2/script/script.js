const express = require('express')
const app = express()
app.use(express.static('.'))//Tornar disponível alguns arquivos (pasta) - . (pasta base do servidor)
app.use(express.json())//Transformar a requisição em JSON
app.use(express.urlencoded({extended:true}))//Permite a leitura quando coloca algo no body

//Servidor iniciado
app.listen(8080, () => {
    console.log(`Servidor iniciado!`)
})



