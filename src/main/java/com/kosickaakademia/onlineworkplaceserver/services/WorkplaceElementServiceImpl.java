package com.kosickaakademia.onlineworkplaceserver.services;

import com.kosickaakademia.onlineworkplaceserver.entities.workplaceelement.CheckListEntity;
import com.kosickaakademia.onlineworkplaceserver.entities.workplaceelement.NoteEntity;
import com.kosickaakademia.onlineworkplaceserver.entities.workplaceelement.ThreadEntity;
import com.kosickaakademia.onlineworkplaceserver.entities.workplaceelement.WorkplaceElementEntity;
import com.kosickaakademia.onlineworkplaceserver.repositories.CheckListRepository;
import com.kosickaakademia.onlineworkplaceserver.repositories.NoteRepository;
import com.kosickaakademia.onlineworkplaceserver.repositories.ThreadRepository;
import com.kosickaakademia.onlineworkplaceserver.repositories.UserRepository;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkplaceElementServiceImpl implements WorkplaceElementService {
    private final NoteRepository noteRepository;
    private final ThreadRepository threadRepository;
    private final CheckListRepository checkListRepository;
    private final UserRepository userRepository;

    public WorkplaceElementServiceImpl(NoteRepository noteRepository, ThreadRepository threadRepository, CheckListRepository checkListRepository, UserRepository userRepository) {
        this.noteRepository = noteRepository;
        this.threadRepository = threadRepository;
        this.checkListRepository = checkListRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<WorkplaceElementEntity> getAllElements(Long workplaceId) {
        List<WorkplaceElementEntity> list = new ArrayList<>();
        list.addAll(noteRepository.getAllByWorkplaceEntityIdAndArchivedLike(workplaceId, true));
        list.addAll(threadRepository.getAllByWorkplaceEntityIdAndArchivedLike(workplaceId, true));
        list.addAll(checkListRepository.getAllByWorkplaceEntityIdAndArchivedLike(workplaceId, true));
        return list;
    }

    @Override
    public WorkplaceElementEntity addElement(WorkplaceElementEntity workplaceElementEntity) {
        if (workplaceElementEntity instanceof NoteEntity) {

            val users = ((NoteEntity) workplaceElementEntity).getAssignedUsers()
                    .stream()
                    .map(userEntity -> userRepository.findUserEntityById(userEntity.getId()))
                    .collect(Collectors.toList());

            ((NoteEntity) workplaceElementEntity).getAssignedUsers().clear();
            ((NoteEntity) workplaceElementEntity).getAssignedUsers().addAll(users);
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
    public void archiveElement(Long elementId, WorkplaceElementEntity workplaceElementEntity) {

    }

    @Override
    public List<WorkplaceElementEntity> getAllArchivedElements(Long workplaceId) {
        return null;
    }
}
