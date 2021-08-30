const express = require('express');
const mongodb = require('mongodb');

const router = express.Router();


const uri = "mongodb+srv://marcos8370:Acer2016@cluster0.72glp.mongodb.net/myFirstDatabase?retryWrites=true&w=majority";
const client = new mongodb.MongoClient(uri, { useNewUrlParser: true, useUnifiedTopology: true });

//Coleta
    //Listar todos os posts de Coleta
    async function loadPostsCollectionColeta(){
        await client.connect();
        return client.db('Veferoma').collection('Coleta');
    }
        //GET all
    router.get("/Coleta",async(req,res)=>{
        const posts = await loadPostsCollectionColeta();
        res.send(await posts.find({}).toArray());
    })
        //GET :campus
    router.get("/Coleta/:campus",async(req,res)=>{
        const posts = await loadPostsCollectionColeta();
        res.send(await posts.find({
            if: req.params.campus
        }).toArray());
    })
     //Crar posts (DEV)
    router.post('/Coleta', async(req,res)=>{
        const posts = await loadPostsCollectionColeta();
       await posts.insertOne({
            qtd_Coletada: req.body.qtd_Coletada,
            if: req.body.if,
            if_cod: req.body.if_cod,
            qtd_Coletada_Total: req.body.qtd_Coletada_Total,
            tipoLixo: req.body.tipoLixo,
            data: req.body.data
       });
       res.status(201).send();
    })
    //Update posts
    router.put('/Coleta/:if/:tipoLixo', async(req, res)=>{
        const posts = await loadPostsCollectionColeta();
       await posts.updateOne({
           if: req.params.if,
           tipoLixo: req.params.tipoLixo
       }, {
        $set:{qtd_Coletada: req.body.qtd_Coletada},
        $set:{qtd_Coletada_Total: req.body.qtd_Coletada_Total},
        $set:{data: req.body.data}
       })
       res.status(200).send();
    })
    //Apagar posts (DEV)
    router.delete('/Coleta/:id', async(req,res)=>{
        const posts = await loadPostsCollectionColeta();
        await posts.deleteOne({
            _id:  new mongodb.ObjectID(req.params.id)
        });
        res.status(200).send();
    })

//Feedback
    //Listar todos os posts de Feedback
    async function loadPostsCollectionFeedback(){
        await client.connect();
        return client.db('Veferoma').collection('Feedback');
    }
        //GET all
    router.get("/Feedback",async(req,res)=>{
        const posts = await loadPostsCollectionFeedback();
        res.send(await posts.find({}).toArray());
    })
    //     //GET :campus
    // router.get("/Feedback/:campus",async(req,res)=>{
    //     const posts = await loadPostsCollectionFeedback();
    //     res.send(await posts.find({
    //         if: req.params.campus
    //     }).toArray());
    // })
     //Crar posts
    router.post('/Feedback', async(req,res)=>{
        const posts = await loadPostsCollectionFeedback();
       await posts.insertOne({
            if: req.body.if,
            if_cod: req.body.if_cod,
           titulo: req.body.titulo,
            texto: req.body.texto,
            op: req.body.op
       });
       res.status(201).send();
    })
    //Apagar posts (DEV)
    router.delete('/Feedback/:id', async(req,res)=>{
        const posts = await loadPostsCollectionFeedback();
        await posts.deleteOne({
            _id:  new mongodb.ObjectID(req.params.id)
        });
        res.status(200).send();
    })


//Instituto
    //Listar todos os posts de Istituto
    async function loadPostsCollectionInstituto(){
        await client.connect();
        return client.db('Veferoma').collection('Instituto');
    }
        //GET all
    router.get("/Instituto",async(req,res)=>{
        const posts = await loadPostsCollectionInstituto();
        res.send(await posts.find({}).toArray());
    })
        //GET :campus
    router.get("/Instituto/:campus",async(req,res)=>{
        const posts = await loadPostsCollectionInstituto();
        res.send(await posts.find({
            campus: req.params.campus
        }).toArray());
    })
    
    // //Crar posts
    // router.post('/Instituto', async(req,res)=>{
    //     const posts = await loadPostsCollection();
    //    await posts.insertOne({
    //         campus: req,
    //         data: "12/05"
    //    });
    //    res.status(201).send();
    // })
    //Apagar posts
    // router.delete('/:id', async(req,res)=>{
    //     const posts = await loadPostsCollection();
    //     await posts.deleteOne({
    //         _id:  new mongodb.ObjectID(req.params.id)
    //     });
    //     res.status(201).send();
    // })
    
    
//Mudanca
    //Listar todos os posts de Mudanca
    async function loadPostsCollectionMudanca(){
        await client.connect();
        return client.db('Veferoma').collection('Mudanca');
    }
        //GET all (DEV)
    router.get("/Mudanca",async(req,res)=>{
        const posts = await loadPostsCollectionMudanca();
        res.send(await posts.find({}).toArray());
    })
    //     //GET :campus
    // router.get("/Feedback/:campus",async(req,res)=>{
    //     const posts = await loadPostsCollectionMudanca();
    //     res.send(await posts.find({
    //         if: req.params.campus
    //     }).toArray());
    // })
     //Crar posts
    router.post('/Mudanca', async(req,res)=>{
        const posts = await loadPostsCollectionMudanca();
       await posts.insertOne({
            ponto: req.body.ponto,
            det: req.body.det,
            mapa:null,
            if: req.body.if,
            if_cod: req.body.if_cod
       });
       res.status(201).send();
    })
    //Apagar posts (DEV)
    router.delete('/Mudanca/:id', async(req,res)=>{
        const posts = await loadPostsCollectionMudanca();
        await posts.deleteOne({
            _id:  new mongodb.ObjectID(req.params.id)
        });
        res.status(200).send();
    })


//Ponto
    //Listar todos os posts de Ponto
    async function loadPostsCollectionPonto(){
        await client.connect();
        return client.db('Veferoma').collection('Ponto');
    }
        //GET all (DEV)
    router.get("/Ponto",async(req,res)=>{
        const posts = await loadPostsCollectionPonto();
        res.send(await posts.find({}).toArray());
    })
        //GET :if
    router.get("/Ponto/:if",async(req,res)=>{
        const posts = await loadPostsCollectionPonto();
        res.send(await posts.find({
            if: req.params.if
        }).toArray());
    })
     //Crar posts (DEV)
    router.post('/Ponto', async(req,res)=>{
        const posts = await loadPostsCollectionPonto();
       await posts.insertOne({
            ponto: req.body.ponto,
            det: req.body.det,
            mapa:null,
            if: req.body.if,
            if_cod: req.body.if_cod
       });
       res.status(201).send();
    })
    //Apagar posts (DEV)
    router.delete('/Ponto/:id', async(req,res)=>{
        const posts = await loadPostsCollectionPonto();
        await posts.deleteOne({
            _id:  new mongodb.ObjectID(req.params.id)
        });
        res.status(200).send();
    })


//TipoLixo (DEV)
    /* //Listar todos os posts de Ponto
    async function loadPostsCollectionTipoLixo(){
        await client.connect();
        return client.db('Veferoma').collection('Tipo_lixo');
    }
        //GET all (DEV)
    router.get("/Tipo",async(req,res)=>{
        const posts = await loadPostsCollectionTipoLixo();
        res.send(await posts.find({}).toArray());
    })
    //     //GET :id
    // router.get("/Tipo/:id",async(req,res)=>{
    //     const posts = await loadPostsCollectionTipoLixo();
    //     res.send(await posts.find({
    //         id: req.params.id
    //     }).toArray());
    // })
     //Crar posts (DEV)
    router.post('/Tipo', async(req,res)=>{
        const posts = await loadPostsCollectionTipoLixo();
       await posts.insertOne({
            ponto: req.body.ponto,
            det: req.body.det,
            mapa:null,
            if: req.body.if,
            if_cod: req.body.if_cod
       });
       res.status(201).send();
    })
    //Apagar posts (DEV)
    router.delete('/Tipo/:id', async(req,res)=>{
        const posts = await loadPostsCollectionTipoLixo();
        await posts.deleteOne({
            _id:  new mongodb.ObjectID(req.params.id)
        });
        res.status(200).send();
    }) */

//Usuário
    //Listar todos os posts de Coleta
    async function loadPostsCollectionUsuario(){
        await client.connect();
        client.db('Veferoma').collection('Usuario').createIndex({email:1},{unique:true})
        return client.db('Veferoma').collection('Usuario');
    }
        //GET all
    router.get("/Usuario",async(req,res)=>{
        const posts = await loadPostsCollectionUsuario();
        res.send(await posts.find({}).toArray());
    })
        //GET :usr
    router.get("/Usuario/:usr",async(req,res)=>{
        const posts = await loadPostsCollectionUsuario();
        res.send(await posts.find({
            usuario: req.params.usr
        }).toArray());
    })
    
     //Crar posts
    router.post('/Usuario', async(req,res)=>{
        const posts = await loadPostsCollectionUsuario();
       await posts.insertOne({
            nome: req.body.nome,
            usuario: req.body.usuario,
            senha: req.body.senha,
            if: req.body.if,
            if_cod: req.body.if_cod,
            email: req.body.email,
            ra: req.body.ra,
            adm: req.body.adm
       });
       res.status(201).send();
    })
    //Update posts
    router.put('/usuario/:obj', async(req, res)=>{
        const posts = await loadPostsCollectionUsuario();
        if(req.body.senha !=null){
            await posts.updateOne({
                usuario: req.params.obj
            }, {
             $set:{senha: req.body.senha}
            })
        }
        if(req.body.email != null){
            await posts.updateOne({
                usuario: req.params.obj
            }, {
             $set:{usuario: req.body.email}
            })
        }
        if(req.body.usuario != null){
            await posts.updateOne({
                usuario: req.params.obj
            }, {
             $set:{usuario: req.body.usuario}
            })
        }
       res.status(200).send();
    })
    //Apagar posts (DEV)
    router.delete('/Usuario/:id', async(req,res)=>{
        const posts = await loadPostsCollectionUsuario();
        await posts.deleteOne({
            _id:  new mongodb.ObjectID(req.params.id)
        });
        res.status(200).send();
    })



module.exports = router;