package com.example.appmeuslivros

class PV_livro(var imagem:Int, var nome1:String) {
    companion object{
        fun getListaLivrosclassicos(): List<PV_livro> {

            val listaLivrosclassicos = ArrayList<PV_livro>()
            listaLivrosclassicos.add(PV_livro(R.drawable.machadodeassis, "Machado de Assis"))
            listaLivrosclassicos.add(PV_livro(R.drawable.machadodeassi2, "Machado de Assis"))
            listaLivrosclassicos.add(PV_livro(R.drawable.gracilianoramos, "Graciliano Ramos"))
            listaLivrosclassicos.add(PV_livro(R.drawable.josedealencar, "José de Alencar"))
            listaLivrosclassicos.add(PV_livro(R.drawable.bernardouimaraes, "Bernardo Guimarães"))
            listaLivrosclassicos.add(PV_livro(R.drawable.castroalves, "Castro Alves"))
            listaLivrosclassicos.add(PV_livro(R.drawable.euclidesdacunha, "Euclides da Cunha"))
            listaLivrosclassicos.add(PV_livro(R.drawable.euclidesdacunha2, "Euclides da Cunha"))
            listaLivrosclassicos.add(PV_livro(R.drawable.guimaraesrosa, "Guimarães Rosa"))
            listaLivrosclassicos.add(PV_livro(R.drawable.joaquimmanoeldemacedo, "Joaquim Manoel de Macedo"))
            return listaLivrosclassicos
        }
    }
}