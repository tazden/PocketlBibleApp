package com.example.digitalbibleapp.data

import com.example.digitalbibleapp.core.Abstract
import com.example.digitalbibleapp.domain.BookDomain



sealed class BookData: Abstract.Object<BookDomain, Abstract.Mapper.Empty>(){
}