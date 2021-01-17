package com.kosickaakademia.onlineworkplaceserver.services;

import com.kosickaakademia.onlineworkplaceserver.entities.workplaceelement.CheckListEntity;
import com.kosickaakademia.onlineworkplaceserver.entities.workplaceelement.NoteEntity;
import com.kosickaakademia.onlineworkplaceserver.entities.workplaceelement.ThreadEntity;
import com.kosickaakademia.onlineworkplaceserver.entities.workplaceelement.WorkplaceElementEntity;
import com.kosickaakademia.onlineworkplaceserver.repositories.CheckListRepository;
import com.kosickaakademia.onlineworkplaceserver.repositories.NoteRepository;
import com.kosickaakademia.onlineworkplaceserver.repositories.ThreadRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkplaceElementServiceImpl implements WorkplaceElementService {
    private final NoteRepository noteRepository;
    private final ThreadRepository threadRepository;
    private final CheckListRepository checkListRepository;

    public WorkplaceElementServiceImpl(NoteRepository noteRepository, ThreadRepository threadRepository, CheckListRepository checkListRepository) {
        this.noteRepository = noteRepository;
        this.threadRepository = threadRepository;
        this.checkListRepository = checkListRepository;
    }

    @Override
    public List<WorkplaceElementEntity> getAllElements(Long workplaceId) {
        return null;
    }

    @Override
    public WorkplaceElementEntity addElement(WorkplaceElementEntity workplaceElementEntity) {
        if (workplaceElementEntity instanceof NoteEntity) {
            System.out.println("INSTANCE OF NOTE ENTITY");
            return noteRepository.save((NoteEntity) workplaceElementEntity);
        }
        if (workplaceElementEntity instanceof ThreadEntity) {
            System.out.println("INSTANCE OF THREAD ENTITY");
            return threadRepository.save((ThreadEntity) workplaceElementEntity);
        }
        if (workplaceElementEntity instanceof CheckListEntity) {
            System.out.println("INSTANCE OF CHECKLIST ENTITY");
            return checkListRepository.save((CheckListEntity) workplaceElementEntity);
        }
        return null;
    }

    @Override
    public void updateElement(WorkplaceElementEntity elementEntity) {

    }

    @Override
    public void archiveElement(Long elementId) {

    }
}
