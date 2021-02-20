package com.kosickaakademia.onlineworkplaceserver.services;

import com.kosickaakademia.onlineworkplaceserver.entities.workplaceelement.CheckListEntity;
import com.kosickaakademia.onlineworkplaceserver.entities.workplaceelement.TaskEntity;
import com.kosickaakademia.onlineworkplaceserver.entities.workplaceelement.WorkplaceElementEntity;
import com.kosickaakademia.onlineworkplaceserver.repositories.*;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkplaceElementServiceImpl implements WorkplaceElementService {
    private final UserRepository userRepository;
    private final LabelRepository labelRepository;
    private final WorkplaceRepository workplaceRepository;
    private final WorkplaceElementRepository workplaceElementRepository;

    public WorkplaceElementServiceImpl(UserRepository userRepository, LabelRepository labelRepository, WorkplaceRepository workplaceRepository, WorkplaceElementRepository workplaceElementRepository) {
        this.userRepository = userRepository;
        this.labelRepository = labelRepository;
        this.workplaceRepository = workplaceRepository;
        this.workplaceElementRepository = workplaceElementRepository;
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
    public WorkplaceElementEntity addElement(WorkplaceElementEntity workplaceElementEntity, Long workplaceId) {
        val workplace = workplaceRepository.getWorkplaceEntityById(workplaceId);

        if(workplaceElementEntity instanceof CheckListEntity) {
            for (TaskEntity taskEntity: ((CheckListEntity) workplaceElementEntity).getTaskEntities()){
                val users = taskEntity.getAssignedUsers()
                        .stream().map(userEntity -> userRepository.findUserEntityById(userEntity.getId()))
                        .collect(Collectors.toList());

                taskEntity.getAssignedUsers().clear();
                taskEntity.setAssignedUsers(users);
            }
        }


        if (workplaceElementEntity.getAssignedUsers() != null) {
            val users = workplaceElementEntity.getAssignedUsers()
                    .stream().map(userEntity -> userRepository.findUserEntityById(userEntity.getId()))
                    .collect(Collectors.toList());

            workplaceElementEntity.getAssignedUsers().clear();
            workplaceElementEntity.setAssignedUsers(users);
        }

        val labels = workplaceElementEntity.getAssignedLabels()
                .stream().map(labelEntity -> labelRepository.findLabelEntityById(labelEntity.getId()))
                .collect(Collectors.toList());

        workplaceElementEntity.getAssignedLabels().clear();
        workplaceElementEntity.setAssignedLabels(labels);

        workplaceElementEntity.setWorkplaceEntity(workplace);
        return workplaceElementRepository.save(workplaceElementEntity);
    }

    @Override
    public void archiveElement(Long elementId, WorkplaceElementEntity workplaceElementEntity) {

    }

    @Override
    public List<WorkplaceElementEntity> getAllArchivedElements(Long workplaceId) {
        return null;
    }
}
