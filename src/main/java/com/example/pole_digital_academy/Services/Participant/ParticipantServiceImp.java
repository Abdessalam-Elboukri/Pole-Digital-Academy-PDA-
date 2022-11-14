package com.example.pole_digital_academy.Services.Participant;

import com.example.pole_digital_academy.Dao.DaoFactory;
import com.example.pole_digital_academy.Dao.Participant.IParticipantDao;
import com.example.pole_digital_academy.Dao.Participant.ParticipantDaoImp;
import com.example.pole_digital_academy.Entities.Participant;

import java.util.List;

import static com.example.pole_digital_academy.Dao.DaoFactory.getParticipantDao;

public class ParticipantServiceImp implements IParticipantService{
    IParticipantDao participantDao =new ParticipantDaoImp();

    @Override
    public int delete(int id) throws Exception {
        return 0;
    }

    @Override
    public List<Participant> getAll() throws Exception {
        return DaoFactory.getParticipantDao().getAll();
    }

    @Override
    public Participant findById(int id) throws Exception {
        return DaoFactory.getParticipantDao().findById(id);
    }

    @Override
    public int insert(Participant entity) throws Exception {
        DaoFactory.getParticipantDao().insert(entity);
        return 0;
    }

    @Override
    public int update(Participant entity) throws Exception {
        DaoFactory.getParticipantDao().update1(entity);
        return 0;
    }
}
