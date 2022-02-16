package com.ausias.inmo.api;

import com.ausias.inmo.entity.CiudadEntity;
import com.ausias.inmo.entity.UsuarioEntity;
import com.ausias.inmo.repository.CiudadRepository;
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
@RequestMapping("/ciudad")
public class CiudadController {

    @Autowired
    CiudadRepository oCiudadRepository;

    /*@Autowired
    TipoViviendaService oTipoProductoService;*/
    @Autowired
    HttpSession oHttpSession;

    @GetMapping("/{id}")
    public ResponseEntity<CiudadEntity> get(@PathVariable(value = "id") Long id) {
        if (oCiudadRepository.existsById(id)) {
            return new ResponseEntity<CiudadEntity>(oCiudadRepository.getById(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("")
    public ResponseEntity<Page<CiudadEntity>> getPage(@PageableDefault(page = 0, size = 10, direction = Sort.Direction.DESC) Pageable oPageable,
            @RequestParam(name = "filter", required = false) String strFilter) {
        Page<CiudadEntity> oPage = null;
        if (strFilter != null) {
            oPage = oCiudadRepository.findByNombreIgnoreCaseContaining(strFilter, oPageable);
        } else {
            oPage = oCiudadRepository.findAll(oPageable);
        }
        return new ResponseEntity<>(oPage, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<CiudadEntity>> getall(@PageableDefault(page = 0, size = 50, direction = Sort.Direction.DESC) Pageable oPageable,
            @RequestParam(name = "filter", required = false) String strFilter) {
        Page<CiudadEntity> oPage = null;
        if (strFilter != null) {
            oPage = oCiudadRepository.findByNombreIgnoreCaseContaining(strFilter, oPageable);
        } else {
            oPage = oCiudadRepository.findAll(oPageable);
        }
        return new ResponseEntity<>(oPage, HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return new ResponseEntity<Long>(oCiudadRepository.count(), HttpStatus.OK);
    }

    @GetMapping("/filter/{filtro}")
    public ResponseEntity<Page<CiudadEntity>> getFilteredPage(@PathVariable(value = "filtro") String sfiltro, @PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC) Pageable oPageable) {
        Page<CiudadEntity> oPage = null;
        oPage = oCiudadRepository.findByNombreIgnoreCaseContaining(sfiltro, oPageable);
        return new ResponseEntity<Page<CiudadEntity>>(oPage, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")

    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        UsuarioEntity oSessionUsuarioEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");
        if (oSessionUsuarioEntity == null) {
            return new ResponseEntity<Long>(0L, HttpStatus.UNAUTHORIZED);
        } else {
            if (oSessionUsuarioEntity.getTipousuario().getId() == 1) {
                if (oCiudadRepository.existsById(id)) {
                    oCiudadRepository.deleteById(id);
                    if (oCiudadRepository.existsById(id)) {
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
    public ResponseEntity<?> create(@RequestBody CiudadEntity oCiudadEntity
    ) {
        UsuarioEntity oSessionUsuarioEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");
        if (oSessionUsuarioEntity == null) {
            return new ResponseEntity<Long>(0L, HttpStatus.UNAUTHORIZED);
        } else {
            if (oSessionUsuarioEntity.getTipousuario().getId() == 1) {
                if (oSessionUsuarioEntity.getTipousuario().getId() == 1) {
                    oCiudadEntity.setId(null);
                    return new ResponseEntity<CiudadEntity>(oCiudadRepository.save(oCiudadEntity), HttpStatus.OK);
                } else {
                    return new ResponseEntity<Long>(0L, HttpStatus.UNAUTHORIZED);
                }
            } else {
                return new ResponseEntity<Long>(0L, HttpStatus.UNAUTHORIZED);
            }
        }
    }

    @PutMapping("/")
    public ResponseEntity<?> update(@RequestBody CiudadEntity oCiudadEntity
    ) {
        UsuarioEntity oSessionUsuarioEntity = (UsuarioEntity) oHttpSession.getAttribute("usuario");
        if (oSessionUsuarioEntity == null) {
            return new ResponseEntity<Long>(0L, HttpStatus.UNAUTHORIZED);
        } else {
            if (oSessionUsuarioEntity.getTipousuario().getId() == 1) {
                if (oCiudadRepository.existsById(oCiudadEntity.getId())) {
                    return new ResponseEntity<CiudadEntity>(oCiudadRepository.save(oCiudadEntity), HttpStatus.OK);
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
