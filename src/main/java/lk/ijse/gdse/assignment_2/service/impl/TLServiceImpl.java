package lk.ijse.gdse.assignment_2.service.impl;

import lk.ijse.gdse.assignment_2.dto.TLDto;
import lk.ijse.gdse.assignment_2.entity.TL;
import lk.ijse.gdse.assignment_2.repo.TLRepo;
import lk.ijse.gdse.assignment_2.service.TLService;
import lk.ijse.gdse.assignment_2.util.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TLServiceImpl implements TLService {

    private final TLRepo tlRepo;

    private final ModelMapper modelMapper;

    public TLServiceImpl(TLRepo tlRepo, ModelMapper modelMapper) {
        this.tlRepo = tlRepo;
        this.modelMapper = modelMapper;

    }

    @Override
    public TLDto add(TLDto tlDto) {
        try {
            TL save = tlRepo.save(modelMapper.map(tlDto, TL.class));
            return modelMapper.map(save, TLDto.class);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public boolean delete(UUID uuid) {
        try {
            tlRepo.deleteById(uuid);
            return true;
        } catch (Exception e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    @Override
    public TLDto update(TLDto tlDto) {
        try {
            if (tlRepo.existsById(tlDto.getTlId())) {
                TL save = tlRepo.save(modelMapper.map(tlDto, TL.class));
                return modelMapper.map(save, TLDto.class);
            } else {
                throw new NotFoundException("TL not found");
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public TLDto search(UUID uuid) {
        try {
            Optional<TL> byId = tlRepo.findById(uuid);
            return modelMapper.map(byId, TLDto.class);
        } catch (Exception e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    @Override
    public List<TLDto> getAll() {
        return null;
    }
}
