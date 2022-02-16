package com.ausias.inmo.api;

import com.ausias.inmo.entity.UsuarioEntity;
import com.ausias.inmo.entity.ViviendaEntity;
import com.ausias.inmo.repository.ViviendaRepository;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vivienda")
public class ViviendaController {

    @Autowired
    ViviendaRepository oViviendaRepository;

    /*@Autowired
    TipoViviendaService oTipoProductoService;*/
    @Autowired
    HttpSession oHttpSession;

    @GetMapping("/{id}")
    public ResponseEntity<ViviendaEntity> get(@PathVariable(value = "id") Long id) {
        if (oViviendaRepository.existsById(id)) {
            return new ResponseEntity<ViviendaEntity>(oViviendaRepository.getById(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("")
    public ResponseEntity<Page<ViviendaEntity>> getPage(@PageableDefault(page = 0, size = 10, direction = Sort.Direction.DESC) Pageable oPageable,
            @RequestParam(name = "filter", required = false) String strFilter) {
        Page<ViviendaEntity> oPage = null;
        if (strFilter != null) {
            oPage = oViviendaRepository.findByUbicacionIgnoreCaseContaining(strFilter, oPageable);
        } else {
            oPage = oViviendaRepository.findAll(oPageable);
        }
        return new ResponseEntity<>(oPage, HttpStatus.OK);
    }

    @GetMapping("/busqueda")
    public ResponseEntity<Page<ViviendaEntity>> getBusqueda(@PageableDefault(page = 0, size = 10, direction = Sort.Direction.DESC) Pageable oPageable, 
            @RequestParam(name = "zona", required = false) Long lZona, @RequestParam(name = "ciudad", required = false) Long lCiudad, 
            @RequestParam(name = "comarca", required = false) Long lComarca,@RequestParam(name = "accion", required = false) String accion) {
        Page<ViviendaEntity> oPage = null;
        if (accion.equalsIgnoreCase("alquilar")) {
            if (lCiudad != null) {
                if (lComarca != null) {
                    if (lZona != null) {
                        oPage = oViviendaRepository.findbyAlquilarZona(lZona, oPageable);
                    } else {
                        oPage = oViviendaRepository.findbyAlquilarComarca(lComarca, oPageable);
                    }
                } else {
                    oPage = oViviendaRepository.findbyAlquilarCiudad(lCiudad, oPageable);
                }
            } else {
                return new ResponseEntity<>(oPage, HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(oPage, HttpStatus.OK);
        } else if (accion.equalsIgnoreCase("comprar")) {
            if (lCiudad != null) {
                if (lComarca != null) {
                    if (lZona != null) {
                        oPage = oViviendaRepository.findbyComprarZona(lZona, oPageable);
                    } else {
                        oPage = oViviendaRepository.findbyComprarComarca(lComarca, oPageable);
                    }
                } else {
                    oPage = oViviendaRepository.findbyComprarCiudad(lCiudad, oPageable);
                }
            } else {
                return new ResponseEntity<>(oPage, HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(oPage, HttpStatus.OK);
        } else if (accion.equalsIgnoreCase("obranueva") ) {
            if (lCiudad != null) {
                if (lComarca != null) {
                    if (lZona != null) {
                        oPage = oViviendaRepository.findbyObranuevaZona(lZona, oPageable);
                    } else {
                        oPage = oViviendaRepository.findbyObranuevaComarca(lComarca, oPageable);
                    }
                } else {
                    oPage = oViviendaRepository.findbyObranuevaCiudad(lCiudad, oPageable);
                }
            } else {
                return new ResponseEntity<>(oPage, HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(oPage, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(oPage, HttpStatus.NOT_FOUND);

        }

    }

//        if (lCiudad != null) {
//            if (lComarca != null) {
//                if (lZona != null) {
//                    oPage = oViviendaRepository.findbyZona(lZona, oPageable);
//                } else {
//                    oPage = oViviendaRepository.findbyComarca(lComarca, oPageable);
//                }
//            } else {
//                oPage = oViviendaRepository.findbyCiudad(lCiudad, oPageable);
//            }
//        } else {
//             return new ResponseEntity<>(oPage, HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(oPage, HttpStatus.OK);
//    @GetMapping("/busqueda")
//    public ResponseEntity<Page<ViviendaEntity>> getBusqueda(@PageableDefault(page = 0, size = 5, direction = Sort.Direction.DESC) Pageable oPageable,
//           @RequestParam(name = "comprar", required = false) Boolean comprar,
//           @RequestParam(name = "alquilar", required = false) Boolean alquilar, @RequestParam(name = "obranueva", required = false) Boolean obranueva,
//           @RequestParam(name = "tipovivienda", required = false) Long tipovivienda, @RequestParam(name = "conservacion", required = false) String conservacion,
//           @RequestParam(name = "habitaciones", required = false) Integer habitaciones, @RequestParam(name = "antiguedad", required = false) Integer antiguedad,
//           @RequestParam(name = "profesional", required = false) Boolean profesional, @RequestParam(name = "ciudad", required = false) Long lCiudad,
//           @RequestParam(name = "comarca", required = false) Long lComarca, @RequestParam(name = "zona", required = false) Long lZona) {
//        Page<ViviendaEntity> oPage = null;
//        if (alquilar==null){
//            alquilar=false;
//        }
//        if (comprar==null){
//            comprar=false;
//        }
//        if (obranueva==null){
//            obranueva=false;
//        }
//             if (comprar == true) {
//
//                    if (tipovivienda != null) {
//                        if (conservacion != null) {
//                            if (habitaciones != null) {
//                                if (antiguedad != null) {
//                            if (profesional != null) {
//                            if (lCiudad != null) {
//                            if (lComarca != null) {
//                            if (lZona != null) {
//
//                            oPage = oViviendaRepository.findByCompraAlquilarObranuevaAndTipoViviendaAndConservacionAndHabitacionesAndAntiguedadAndProfesionalAndZona(comprar,alquilar,obranueva,tipovivienda,conservacion,habitaciones, antiguedad,lZona, profesional,oPageable);
//
//                    } else {
//                                oPage = oViviendaRepository.findByCompraAlquilarObranuevaAndTipoViviendaAndConservacionAndHabitacionesAndAntiguedadAndProfesionalAndComarca(comprar,alquilar,obranueva,tipovivienda,conservacion,habitaciones, antiguedad,lComarca, profesional,oPageable);
//                            }
//
//
//                    } else {
//                                oPage = oViviendaRepository.findByCompraAlquilarObranuevaAndTipoViviendaAndConservacionAndHabitacionesAndAntiguedadAndProfesionalAndCiudad(comprar,alquilar,obranueva,tipovivienda,conservacion,habitaciones, antiguedad,lCiudad, profesional,oPageable);
//                            }
//
//
//                    }else {
//                                oPage = oViviendaRepository.findByCompraAlquilarObranuevaAndTipoViviendaAndConservacionAndHabitacionesAndAntiguedadAndProfesional(comprar,alquilar,obranueva,tipovivienda,conservacion,habitaciones, antiguedad, profesional,oPageable);
//                            }
//
//
//                    }
//
//
//                    }
//
//
//                    }
//
//
//                    }
//                    }
//                        oPage = oCompraRepository.findByFacturaIdAndCantidadOrPrecioOrFechaOrDescuentoUsuarioOrDescuentoProducto(lFactura, strFilter, strFilter, strFilter, strFilter, strFilter, oPageable);
//                    } else {
//                        oPage = oCompraRepository.findByFacturaId(lFactura, oPageable);
//                    }
//
//        return new ResponseEntity<>(oPage, HttpStatus.OK);
//    }
//
    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return new ResponseEntity<Long>(oViviendaRepository.count(), HttpStatus.OK);
    }

    @GetMapping("/filter/{filtro}")
    public ResponseEntity<Page<ViviendaEntity>> getFilteredPage(@PathVariable(value = "filtro") String sfiltro, @PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC) Pageable oPageable) {
        Page<ViviendaEntity> oPage = null;
        oPage = oViviendaRepository.findByUbicacionIgnoreCaseContaining(sfiltro, oPageable);
        return new ResponseEntity<Page<ViviendaEntity>>(oPage, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")

    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        ViviendaEntity oViviendaEntity = oViviendaRepository.getById(id);
        UsuarioEntity oSessionUsuarioEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");
        if (oSessionUsuarioEntity == null) {
            return new ResponseEntity<Long>(0L, HttpStatus.UNAUTHORIZED);
        } else {
            if (oSessionUsuarioEntity.getTipousuario().getId() == 1 || oViviendaEntity.getUsuario().getId() == oSessionUsuarioEntity.getId()) {
                if (oViviendaRepository.existsById(id)) {
                    oViviendaRepository.deleteById(id);
                    if (oViviendaRepository.existsById(id)) {
                        return new ResponseEntity<Long>(0L, HttpStatus.NOT_MODIFIED);
                    } else {
                        return new ResponseEntity<Long>(id, HttpStatus.OK);
                    }
                } else {
                    return new ResponseEntity<Long>(0L, HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity<Long>(0L, HttpStatus.UNAUTHORIZED);
            }
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody ViviendaEntity oViviendaEntity
    ) {
        UsuarioEntity oSessionUsuarioEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");
        if (oSessionUsuarioEntity == null) {
            return new ResponseEntity<Long>(0L, HttpStatus.UNAUTHORIZED);
        } else {
            if (oSessionUsuarioEntity.getTipousuario().getId() == 1 || oViviendaEntity.getUsuario().getId() == oSessionUsuarioEntity.getId()) {

                oViviendaEntity.setId(null);
                return new ResponseEntity<ViviendaEntity>(oViviendaRepository.save(oViviendaEntity), HttpStatus.OK);
            } else {
                return new ResponseEntity<Long>(0L, HttpStatus.UNAUTHORIZED);
            }

        }
    }

    @PutMapping("/")
    public ResponseEntity<?> update(@RequestBody ViviendaEntity oViviendaEntity
    ) {
        UsuarioEntity oSessionUsuarioEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");
        if (oSessionUsuarioEntity == null) {
            return new ResponseEntity<Long>(0L, HttpStatus.UNAUTHORIZED);
        } else {
            if (oSessionUsuarioEntity.getTipousuario().getId() == 1 || oViviendaEntity.getUsuario().getId() == oSessionUsuarioEntity.getId()) {
                if (oViviendaRepository.existsById(oViviendaEntity.getId())) {
                    return new ResponseEntity<ViviendaEntity>(oViviendaRepository.save(oViviendaEntity), HttpStatus.OK);
                } else {
                    return new ResponseEntity<Long>(0L, HttpStatus.NOT_MODIFIED);
                }
            } else {
                return new ResponseEntity<Long>(0L, HttpStatus.UNAUTHORIZED);
            }
        }
    }

    /*@PostMapping("/generate/{amount}")
    public ResponseEntity<?> generateAmount(@PathVariable(value = "amount") int amount) {
        UsuarioEntity oUsuarioEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");
        if (oUsuarioEntity.getTipousuario().getId() == 1) {
            if (oUsuarioEntity == null) {
                return new ResponseEntity<>(0L, HttpStatus.UNAUTHORIZED);
            } else {
                for (int i = 0; i < amount; i++) {
                    TipoProductoEntity oTipoProductoEntity = oTipoProductoService.generateTipoProducto();
                    oTipoProductoRepository.save(oTipoProductoEntity);
                }
                return new ResponseEntity<>(oTipoProductoRepository.count(), HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>(0L, HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/generate")
    public ResponseEntity<?> generate() {
        UsuarioEntity oSessionUsuarioEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");
        if (oSessionUsuarioEntity == null) {
            return new ResponseEntity<Long>(0L, HttpStatus.UNAUTHORIZED);
        } else {
            if (oSessionUsuarioEntity.getTipousuario().getId() == 1) {
                if (oSessionUsuarioEntity.getTipousuario().getId() == 1) {
                    List<TipoProductoEntity> ListaTipoProd = oTipoProductoService.generateAllTipoProductoList();
                    for (int i = 0; i < ListaTipoProd.size(); i++) {
                        oTipoProductoRepository.save(ListaTipoProd.get(i));
                    }
                    return new ResponseEntity<>(oTipoProductoRepository.count(), HttpStatus.OK);

                } else {
                    return new ResponseEntity<>(0L, HttpStatus.UNAUTHORIZED);
                }
            } else {
                return new ResponseEntity<>(0L, HttpStatus.UNAUTHORIZED);
            }
        }
    }*/
}
