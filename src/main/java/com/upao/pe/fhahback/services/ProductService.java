package com.upao.pe.fhahback.services;

import com.upao.pe.fhahback.models.*;
import com.upao.pe.fhahback.repositories.BrandProductRepository;
import com.upao.pe.fhahback.repositories.InventoryRepository;
import com.upao.pe.fhahback.repositories.ProductRepository;
import com.upao.pe.fhahback.serializers.brand.BrandSerializer;
import com.upao.pe.fhahback.serializers.brandproduct.BrandProductSerializer;
import com.upao.pe.fhahback.serializers.color.ColorSerializer;
import com.upao.pe.fhahback.serializers.inventory.InventorySerializer;
import com.upao.pe.fhahback.serializers.product.*;
import com.upao.pe.fhahback.serializers.size.SizeSerializer;
import com.upao.pe.fhahback.serializers.supplier.SupplierSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    InventoryRepository inventoryRepository;
    @Autowired
    BrandProductRepository brandProductRepository;
    @Autowired ColorService colorService;
    @Autowired BrandService brandService;
    @Autowired SupplierService supplierService;
    @Autowired SizeService sizeService;

    // CREATE - FHAH
    public ProductSerializer createProductFHAH(CreateProductFHAHRequest request){
        Product product = new Product();
        Size size; Color color; Supplier supplier;
        if(sizeService.existsSize(request.getSize())){
            size = sizeService.getSize(request.getSize());
        } else{
            size = sizeService.createSize(request.getSize());
        }
        if(colorService.existsColor(request.getColor())){
            color = colorService.getColor(request.getColor());
        } else{
            color = colorService.createColor(request.getColor());
        }
        if(supplierService.existsSupplier(request.getSupplierName())){
            supplier = supplierService.getSupplier(request.getSupplierName());
        } else{
            supplier = supplierService.createSupplier(request.getSupplierName());
        }
        Inventory inventory = new Inventory(null, request.getBarcode(), request.getQuantity(), supplier, size, color, null);
        if(productRepository.existsByCode(request.getCode())){
            Product productFounded = getProduct(request.getCode());
            inventory.setProduct(productFounded);
            boolean found = false;
            for(Inventory inventory1 : productFounded.getInventories()){
                if(inventory.equals(inventory1)){
                    inventory1.setQuantity(inventory1.getQuantity()+1);
                    inventoryRepository.saveAndFlush(inventory1);
                    found = true;
                    break;
                }
            }
            if(!found){
                productFounded.getInventories().add(inventory);
                productRepository.saveAndFlush(productFounded);
                return returnProductSerializer(productFounded);
            }
        }
        else{
            product = new Product(null, request.getCode(), request.getPurchasePrice(), request.getName(), request.getModel(), request.getSalesPrice(), request.getPhoto(), new ArrayList<>(), new ArrayList<>());
            productRepository.save(product);
            inventory.setProduct(product);
            inventoryRepository.save(inventory);
        }
        return returnProductSerializer(product);
    }

    // CREATE - RH
    public ProductSerializer createProductRH(CreateProductRHRequest request){
        Product product = new Product();
        Brand brand;
        if(brandService.existsBrand(request.getBrandName())){
            brand = brandService.getBrand(request.getBrandName());
        } else{
            brand = brandService.createBrand(request.getBrandName());
        }
        BrandProduct brandProduct = new BrandProduct(null, request.getBarcode(), request.getQuantity(), brand, null);
        if(productRepository.existsByCode(request.getCode())){
            Product productFounded = getProduct(request.getCode());
            brandProduct.setProduct(productFounded);
            boolean found = false;
            for(BrandProduct brandProduct1 : productFounded.getBrandProducts()){
                if(brandProduct.equals(brandProduct1)){
                    brandProduct1.setQuantity(brandProduct1.getQuantity()+1);
                    brandProductRepository.saveAndFlush(brandProduct1);
                    found = true;
                    break;
                }
            }
            if(!found){
                productFounded.getBrandProducts().add(brandProduct);
                productRepository.saveAndFlush(productFounded);
                return returnProductSerializer(productFounded);
            }
        }
        else{
            product = new Product(null, request.getCode(), request.getPurchasePrice(), request.getName(), null, request.getSalesPrice(), request.getPhoto(), new ArrayList<>(), new ArrayList<>());
            productRepository.save(product);
            brandProduct.setProduct(product);
            brandProductRepository.save(brandProduct);
        }
        return returnProductSerializer(product);
    }

    // READ - ALL
    public List<ProductSerializer> listProducts(){return productRepository.findAll().stream().map(this::returnProductSerializer).toList();}

    // READ - FHAH
    public List<ProductSerializer> listProductsFHAH() {
        return productRepository.findAll().stream()
                .map(it -> {
                    if (!it.getInventories().isEmpty() && it.getBrandProducts().isEmpty()) {
                        return returnProductSerializer(it);
                    }
                    return null; // Opción para manejar el caso cuando no cumple la condición
                })
                .filter(Objects::nonNull) // Elimina los nulos del resultado
                .toList();
    }

    // READ - RH
    public List<ProductSerializer> listProductsRH() {
        return productRepository.findAll().stream()
                .map(it -> {
                    if (it.getInventories().isEmpty() && !it.getBrandProducts().isEmpty()) {
                        return returnProductSerializer(it);
                    }
                    return null; // Opción para manejar el caso cuando no cumple la condición
                })
                .filter(Objects::nonNull) // Elimina los nulos del resultado
                .toList();
    }

    // UPDATE

    // DELETE

    // SERIALIZER
    public ProductSerializer returnProductSerializer(Product product){
        List<InventorySerializer> inventories = new ArrayList<>();
        List<BrandProductSerializer> brands = new ArrayList<>();
        for(Inventory inventory : product.getInventories()){
            InventorySerializer inventorySerializer = new InventorySerializer(inventory.getBarcode(), inventory.getQuantity(), new SupplierSerializer(inventory.getSupplier().getSuppliersName().toString()), new SizeSerializer(inventory.getSize().getSize().toString()), new ColorSerializer(inventory.getColor().getColorName()));
            inventories.add(inventorySerializer);
        }
        for(BrandProduct brand : product.getBrandProducts()){
            BrandProductSerializer brandProductSerializer = new BrandProductSerializer(brand.getBarcode(), brand.getQuantity(), new BrandSerializer(brand.getBrand().getBrandName()));
            brands.add(brandProductSerializer);
        }
        return new ProductSerializer(product.getCode(), product.getName(), product.getModel(), product.getPurchasePrice(), product.getSalesPrice(),  product.getPhoto(), inventories, brands);
    }


    // GET
    public Product getProduct(String code){
        Optional<Product> product = productRepository.findByCode(code);
        if(product.isEmpty()){
            throw new RuntimeException("Producto no encontrado");
        }
        return product.get();
    }

    // FILTER PRODUCTS FHAH
    public List<DetailsProductSerializer> filterProductsFHAH(FilterProductsFHAHRequest request){
        List<Color> colors = colorService.getColors(request.getColors());
        List<Size> sizes = sizeService.getSizes(request.getSizes());
        List<Supplier> suppliers = supplierService.getSuppliers(request.getSuppliers());

        // Verificar si algún filtro enviado por el usuario no tiene coincidencias en la base de datos
        if ((colors != null && colors.isEmpty()) ||
                (sizes != null && sizes.isEmpty()) ||
                (suppliers != null && suppliers.isEmpty())) {
            return List.of(); // Retorna vacío si algún filtro enviado no tiene coincidencias
        }

        // Aplicar el filtrado acumulativo solo con los filtros que se enviaron
        List<Inventory> results = inventoryRepository.findAll().stream()
                .filter(inventory ->
                        (colors == null || colors.contains(inventory.getColor())) &&
                                (sizes == null || sizes.contains(inventory.getSize())) &&
                                (suppliers == null || suppliers.contains(inventory.getSupplier()))
                )
                .toList();

        // Mapear los resultados a `ProductSerializer`
        return results.stream()
                .map(this::returnDetailsProductSerializerFHAH)
                .toList();
    }

    // FILTER PRODUCT RH
    public List<DetailsProductSerializer> filterProductsRH(FilterProductsRHRrequest request){
        List<Brand> brands = brandService.getBrands(request.getBrands());

        // Verificar si algún filtro enviado por el usuario no tiene coincidencias en la base de datos
        if (brands != null && brands.isEmpty()){
            return List.of(); // Retorna vacío si algún filtro enviado no tiene coincidencias
        }

        // Aplicar el filtrado acumulativo solo con los filtros que se enviaron
        List<BrandProduct> results = brandProductRepository.findAll().stream()
                .filter(brandProduct -> brands == null || brands.contains(brandProduct.getBrand())).toList();

        // Mapear los resultados a `ProductSerializer`
        return results.stream()
                .map(this::returnDetailsProductSerializerRH)
                .toList();
    }

    public DetailsProductSerializer returnDetailsProductSerializerFHAH(Inventory inventory){
        Product product = inventory.getProduct();
        InventorySerializer inventorySerializer = new InventorySerializer(inventory.getBarcode(), inventory.getQuantity(), new SupplierSerializer(inventory.getSupplier().getSuppliersName().toString()), new SizeSerializer(inventory.getSize().getSize().toString()), new ColorSerializer(inventory.getColor().getColorName()));
        return new DetailsProductSerializer(product.getCode(), product.getPhoto(), product.getName(), product.getModel(), product.getPurchasePrice(), product.getSalesPrice(), inventorySerializer, null);
    }

    public DetailsProductSerializer returnDetailsProductSerializerRH(BrandProduct brand){
        Product product = brand.getProduct();
        BrandProductSerializer brandSerializer = new BrandProductSerializer(brand.getBarcode(), brand.getQuantity(), new BrandSerializer(brand.getBrand().getBrandName()));
        return new DetailsProductSerializer(product.getCode(), product.getPhoto(), product.getName(), product.getModel(), product.getPurchasePrice(), product.getSalesPrice(), null, brandSerializer);
    }

    // SEARCH PRODUCTS
    public List<DetailsProductSerializer> searchProducts(String request){
        List<Product> products = productRepository.findAllByNameContainingIgnoreCase(request);
        List<DetailsProductSerializer> detailsProduct = new ArrayList<>();
        products.forEach(product -> {
            if(product.getInventories().isEmpty()){
                product.getBrandProducts().forEach(brand -> detailsProduct.add(returnDetailsProductSerializerRH(brand)));
            } else {
                product.getInventories().forEach(inventory -> detailsProduct.add(returnDetailsProductSerializerFHAH(inventory)));
            }
        });
        return detailsProduct;
    }
}