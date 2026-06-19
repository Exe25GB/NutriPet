package com.nutripet.inventario.config; 

import com.nutripet.inventario.model.MovimientoStock;
import com.nutripet.inventario.model.Stock;
import com.nutripet.inventario.repository.MovimientoStockRepository;
import com.nutripet.inventario.repository.StockRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

@Profile("test") 
@Component 
public class DataLoader implements CommandLineRunner { 

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private MovimientoStockRepository movimientoStockRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        Random random = new Random();

        System.out.println("Iniciando la carga de datos falsos para Inventario...");


        for (int i = 0; i < 15; i++) {
            Stock stock = new Stock();
            
            stock.setIdProducto((long) random.nextInt(20) + 1); 
            
            stock.setCantidadActual(faker.number().numberBetween(10, 500));
            stock.setStockMinimo(faker.number().numberBetween(5, 50));
            
            stock.setFechaVencimiento(LocalDate.now().plusDays(faker.number().numberBetween(30, 365)));
            
            stock.setNumeroLote(faker.bothify("LOTE-####-????").toUpperCase());
            stock.setUbicacionBodega(faker.options().option("Pasillo A", "Pasillo B", "Bodega Central", "Cámara Frío"));

            stockRepository.save(stock); 
        }


        for (int i = 0; i < 30; i++) {
            MovimientoStock movimiento = new MovimientoStock();
            
            movimiento.setProductoId((long) random.nextInt(20) + 1);
            
            String tipoMovimiento = faker.options().option("ENTRADA", "SALIDA");
            movimiento.setTipo(tipoMovimiento);
            
            movimiento.setCantidad(faker.number().numberBetween(1, 100));
            
            movimiento.setFechaMovimiento(LocalDateTime.now().minusDays(faker.number().numberBetween(0, 30)));
            
            if (tipoMovimiento.equals("ENTRADA")) {
                movimiento.setMotivo(faker.options().option("Compra a proveedor", "Devolución de cliente", "Ajuste positivo"));
            } else {
                movimiento.setMotivo(faker.options().option("Venta", "Merma", "Producto caducado", "Ajuste negativo"));
            }

            movimientoStockRepository.save(movimiento); 
        }

        System.out.println("¡Carga de datos finalizada! Se insertaron 15 stocks y 30 movimientos.");
    }
}