package com.nutripet.producto.config;

import com.nutripet.producto.model.Categoria;
import com.nutripet.producto.model.Producto;
import com.nutripet.producto.repository.CategoriaRepository;
import com.nutripet.producto.repository.ProductoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

@Profile("test")
@Component
@RequiredArgsConstructor
@Slf4j
public class DataLoader implements CommandLineRunner {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public void run(String... args) throws Exception {

        Faker faker = new Faker();
        Random random = new Random();

        System.out.println("Iniciando la carga de datos falsos con DataFaker...");


        for (int i = 0; i < 5; i++) {
            Categoria categoria = new Categoria();
            categoria.setNombre(faker.commerce().department()); 
            categoriaRepository.save(categoria);
        }

        List<Categoria> categorias = categoriaRepository.findAll();


        for (int i = 0; i < 20; i++) {
            Producto producto = new Producto();
            
            producto.setSku(faker.code().asin()); 
            
            producto.setNombre(faker.commerce().productName());
            
            double pesoAleatorio = faker.number().randomDouble(2, 1, 30);
            producto.setPeso(BigDecimal.valueOf(pesoAleatorio));
            
            producto.setDescripcion(faker.lorem().sentence());
            
            producto.setCategoria(categorias.get(random.nextInt(categorias.size())));
            
            producto.setTipoMascota(faker.options().option("Perro", "Gato", "Ave", "Exótico", "Roedor"));
            producto.setCicloVital(faker.options().option("Cachorro", "Adulto", "Senior", "Todas las edades"));
            
            producto.setMarca(faker.company().name());

            productoRepository.save(producto);
        }

        System.out.println("¡Carga de datos finalizada! Se insertaron 5 categorías y 20 productos.");
    }
}