package br.com.app.domain.agenda.schedule;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.app.domain.agenda.model.Agenda;
import br.com.app.domain.agenda.model.AgendaStatus;
import br.com.app.domain.agenda.repository.AgendaRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(onConstructor_ = @Autowired)
public class AgendaSchedule {

	AgendaRepository agendaRepository;

	@Scheduled(cron = "${schedule.finalized-vote-session:0 0/1 * * * ?}")
	private void run() {

		log.info("Running schedule task to finish agenda vote session!");

		List<Agenda> agendas = agendaRepository.findAgendaVotingsFinalizedAndStatusIsActive();
		agendas.forEach(agenda -> agenda.setStatus(AgendaStatus.FINALIZADO));

		if (!agendas.isEmpty()) {
			try {
				log.info(String.format("Finishing agenda vote session. agendas: %s",
						agendas.stream().map(Agenda::getId).collect(Collectors.toSet())));
				agendaRepository.saveAll(agendas);
			} catch (Exception e) {
				log.error("error to finish agenda vote session!", e);
			}
		} else {
			log.info("There was no agenda to finish vote session!");
		}

	}

}
