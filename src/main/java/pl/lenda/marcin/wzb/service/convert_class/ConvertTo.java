package pl.lenda.marcin.wzb.service.convert_class;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.lenda.marcin.wzb.dto.ClientAccountDto;
import pl.lenda.marcin.wzb.dto.DocumentWzDto;
import pl.lenda.marcin.wzb.entity.ClientAccount;
import pl.lenda.marcin.wzb.entity.DocumentWz;
import pl.lenda.marcin.wzb.entity.TraderAccount;
import pl.lenda.marcin.wzb.service.client_account.ClientAccountImplementation;
import pl.lenda.marcin.wzb.service.trader.TraderServiceImplementation;

/**
 * Created by Promar on 02.11.2016.
 */
@Component
public class ConvertTo {

    @Autowired
    ClientAccountImplementation clientAccountImplementation;
    @Autowired
    TraderServiceImplementation traderServiceImplementation;

    public DocumentWzDto convertDocumentToDto(DocumentWz documentWz){
        DocumentWzDto documentWzDto = new DocumentWzDto();
        documentWzDto.setNumberWZ(documentWz.getNumberWZ());
        documentWzDto.setSubProcess(documentWz.getSubProcess());
        documentWzDto.setClient(documentWz.getClient());
        documentWzDto.setDate(documentWz.getDate());
        documentWzDto.setClientNumber(documentWz.getClientNumber());
        documentWzDto.setNameTeam(documentWz.getNameTeam());
        documentWzDto.setTraderName(documentWz.getTraderName());
        documentWzDto.setBeCorrects(documentWz.isBeCorrects());
        return documentWzDto;
    }

    public DocumentWz convertDocumentToEntity(DocumentWzDto documentWzDto){
        DocumentWz documentWz = new DocumentWz();
        ClientAccount clientAccount = clientAccountImplementation.findByClientName(documentWzDto.getClient());
        TraderAccount traderAccount = traderServiceImplementation.findByTraderSurname(documentWzDto.getTraderName());

        documentWz.setNumberWZ(documentWzDto.getNumberWZ());
        documentWz.setSubProcess(documentWzDto.getSubProcess());
        documentWz.setClient(documentWzDto.getClient());
        documentWz.setDate(documentWzDto.getDate());
        documentWz.setTraderName(documentWzDto.getTraderName());
        documentWz.setBeCorrects(documentWzDto.isBeCorrects());
        documentWz.setNameTeam(traderAccount.getNameTeam());
        documentWz.setClientNumber(clientAccount.getNumberClient());
        return documentWz;
    }

    public ClientAccount convertClientAccountDtoToEntity(ClientAccountDto clientAccountDto){
        ClientAccount clientAccount = new ClientAccount();
        clientAccount.setNameTeam(clientAccountDto.getNameTeam());
        clientAccount.setName(clientAccountDto.getName());
        clientAccount.setNumberClient(clientAccountDto.getNumberClient());
        return clientAccount;
    }
}