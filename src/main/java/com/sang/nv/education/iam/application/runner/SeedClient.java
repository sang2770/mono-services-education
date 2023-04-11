package com.sang.nv.education.iam.application.runner;

import com.sang.nv.education.iamdomain.Client;
import com.sang.nv.education.iamdomain.command.ClientCreateOrUpdateCmd;
import com.sang.nv.education.iamdomain.repository.ClientDomainRepository;
import com.sang.nv.education.iaminfrastructure.persistence.entity.ClientEntity;
import com.sang.nv.education.iaminfrastructure.persistence.repository.ClientEntityRepository;
import com.sang.nv.education.iaminfrastructure.support.enums.ClientCategory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
@Slf4j
public class SeedClient implements CommandLineRunner {
    private final ClientDomainRepository clientDomainRepository;
    private final ClientEntityRepository clientEntityRepository;
    @Override
    public void run(String... args){
        this.initClient();
    }

    @Transactional
    public void initClient()
    {
        List<ClientCategory> clientCategoryList = Arrays.asList(ClientCategory.values());
        List<Client> clients = new ArrayList<Client>();
        clientCategoryList.forEach(clientCategory -> {
            Optional<ClientEntity> clientEntityOptional = this.clientEntityRepository.findByClientName(clientCategory.getClientName());
            if (clientEntityOptional.isPresent())
            {
                return;
            }
            log.info("GenerateClient", clientCategory.getClientName());
            clients.add(new Client(ClientCreateOrUpdateCmd.builder().name(clientCategory.getClientName()).build()));
        });
        this.clientDomainRepository.saveAll(clients);
    }
}
