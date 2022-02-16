package com.ausias.inmo.api;

import com.ausias.inmo.entity.ComarcaEntity;
import com.ausias.inmo.entity.UsuarioEntity;
import com.ausias.inmo.repository.ComarcaRepository;
import java.util.List;
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
@RequestMapping("/comarca")
public class ComarcaController {

    @Autowired
    ComarcaRepository oComarcaRepository;

    /*@Autowired
    TipoViviendaService oTipoProductoService;*/
    @Autowired
    HttpSession oHttpSession;

    @GetMapping("/{id}")
    public ResponseEntity<ComarcaEntity> get(@PathVariable(value = "id") Long id) {
        if (oComarcaRepository.existsById(id)) {
            return new ResponseEntity<ComarcaEntity>(oComarcaRepository.getById(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("")
    public ResponseEntity<Page<ComarcaEntity>> getPage(@PageableDefault(page = 0, size = 10, direction = Sort.Direction.DESC) Pageable oPageable,
            @RequestParam(name = "filter", required = false) String strFilter) {
        Page<ComarcaEntity> oPage = null;
        if (strFilter != null) {
            oPage = oComarcaRepository.findByNombreIgnoreCaseContaining(strFilter, oPageable);
        } else {
            oPage = oComarcaRepository.findAll(oPageable);
        }
        return new ResponseEntity<>(oPage, HttpStatus.OK);
    }

    @GetMapping("/ciudad")
    public ResponseEntity<Page<ComarcaEntity>> getByCiudad(@PageableDefault(page = 0, size = 20, direction = Sort.Direction.DESC) Pageable oPageable,
            @RequestParam(name = "ciudad", required = true) Long lCiudad) {
        Page<ComarcaEntity> oPage = null;
        oPage = oComarcaRepository.findByCiudadId(lCiudad, oPageable);

        return new ResponseEntity<>(oPage, HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return new ResponseEntity<Long>(oComarcaRepository.count(), HttpStatus.OK);
    }

    @GetMapping("/filter/{filtro}")
    public ResponseEntity<Page<ComarcaEntity>> getFilteredPage(@PathVariable(value = "filtro") String sfiltro, @PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC) Pageable oPageable) {
        Page<ComarcaEntity> oPage = null;
        oPage = oComarcaRepository.findByNombreIgnoreCaseContaining(sfiltro, oPageable);
        return new ResponseEntity<Page<ComarcaEntity>>(oPage, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")

    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        UsuarioEntity oSessionUsuarioEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");
        if (oSessionUsuarioEntity == null) {
            return new ResponseEntity<Long>(0L, HttpStatus.UNAUTHORIZED);
        } else {
            if (oSessionUsuarioEntity.getTipousuario().getId() == 1) {
                if (oComarcaRepository.existsById(id)) {
                    oComarcaRepository.deleteById(id);
                    if (oComarcaRepository.existsById(id)) {
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
    public ResponseEntity<?> create(@RequestBody ComarcaEntity oComarcaEntity
    ) {
        UsuarioEntity oSessionUsuarioEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");
        if (oSessionUsuarioEntity == null) {
            return new ResponseEntity<Long>(0L, HttpStatus.UNAUTHORIZED);
        } else {
            if (oSessionUsuarioEntity.getTipousuario().getId() == 1) {
                if (oSessionUsuarioEntity.getTipousuario().getId() == 1) {
                    oComarcaEntity.setId(null);
                    return new ResponseEntity<ComarcaEntity>(oComarcaRepository.save(oComarcaEntity), HttpStatus.OK);
                } else {
                    return new ResponseEntity<Long>(0L, HttpStatus.UNAUTHORIZED);
                }
            } else {
                return new ResponseEntity<Long>(0L, HttpStatus.UNAUTHORIZED);
            }
        }
    }

    @PutMapping("/")
    public ResponseEntity<?> update(@RequestBody ComarcaEntity oComarcaEntity
    ) {
        UsuarioEntity oSessionUsuarioEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");
        if (oSessionUsuarioEntity == null) {
            return new ResponseEntity<Long>(0L, HttpStatus.UNAUTHORIZED);
        } else {
            if (oSessionUsuarioEntity.getTipousuario().getId() == 1) {
                if (oComarcaRepository.existsById(oComarcaEntity.getId())) {
                    return new ResponseEntity<ComarcaEntity>(oComarcaRepository.save(oComarcaEntity), HttpStatus.OK);
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
