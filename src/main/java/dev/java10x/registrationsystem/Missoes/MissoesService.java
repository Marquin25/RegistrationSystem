package dev.java10x.registrationsystem.Missoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MissoesService {

    private MissoesRepository missoesRepository;
    private MissoesMapper missoesMapper;

    public MissoesService(MissoesMapper missoesMapper, MissoesRepository missoesRepository) {
        this.missoesMapper = missoesMapper;
        this.missoesRepository = missoesRepository;
    }

    // LISTAR
    public List<MissoesDTO> listarMissoes() {
        return missoesRepository.findAll()
                .stream()
                .map(missoesMapper::map)
                .collect(Collectors.toList());
    }

    // CRIAR
    public MissoesDTO criarMissao(MissoesDTO dto) {
        MissoesModel model = missoesMapper.map(dto);
        MissoesModel salvo = missoesRepository.save(model);
        return missoesMapper.map(salvo);
    }

    // ALTERAR
    public MissoesDTO alterarMissao(Long id, MissoesDTO dto) {
        MissoesModel model = missoesMapper.map(dto);
        model.setId(id);

        MissoesModel atualizado = missoesRepository.save(model);
        return missoesMapper.map(atualizado);
    }

    // DELETAR
    public void deletarMissao(Long id) {
        missoesRepository.deleteById(id);
    }
}