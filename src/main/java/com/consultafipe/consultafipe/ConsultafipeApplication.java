package com.consultafipe.consultafipe;

import com.consultafipe.consultafipe.model.DadosAnos;
import com.consultafipe.consultafipe.model.DadosCarro;
import com.consultafipe.consultafipe.model.DadosMarca;
import com.consultafipe.consultafipe.model.DadosModelos;
import com.consultafipe.consultafipe.services.ObterDadosAPI;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class ConsultafipeApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ConsultafipeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		Scanner scanner = new Scanner(System.in);
		ObterDadosAPI obterDadosAPI = new ObterDadosAPI();
		String enderecoBase = "https://parallelum.com.br/fipe/api/v1/";

		System.out.println("Qual o tipo de veículo deseja procurar?\n*Carros\n*Motos\n*Caminhoes");
		enderecoBase = enderecoBase.concat(scanner.nextLine()+"/marcas/");
		String json = obterDadosAPI.pegaJson(enderecoBase);
		List<DadosMarca> listaMarcas = mapper.readValue(json, new TypeReference<List<DadosMarca>>() {});
		listaMarcas.stream()
				.sorted(Comparator.comparing(DadosMarca::codigo))
				.forEach(m-> System.out.println(m.codigo()+" - "+ m.nome()));
		System.out.println("Digite o Código da marca que deseja procurar:");
		enderecoBase = enderecoBase.concat(scanner.nextLine()+"/modelos/");
		json = obterDadosAPI.pegaJson(enderecoBase);
		Map<String, List<DadosModelos>> resultadoModelos = mapper.readValue(json, new TypeReference<>() {});

		List<DadosModelos> listaModelos = resultadoModelos.get("modelos");
		listaModelos.stream()
				.sorted(Comparator.comparing(DadosModelos::codigo))
				.forEach(m-> System.out.println(m.codigo() + " - " + m.nome()));

		System.out.println("Digite o Código do modelo que deseja consultar os valores:");
		enderecoBase = enderecoBase.concat(scanner.nextLine()+"/anos/");
		json = obterDadosAPI.pegaJson(enderecoBase);
		DadosAnos[] listaAnos = mapper.readValue(json, DadosAnos[].class);

		List<DadosCarro> listaCarros = new ArrayList<>();

		for (DadosAnos ano : listaAnos){
			String enderecoLoop = enderecoBase + ano.codigo();
			json = obterDadosAPI.pegaJson(enderecoLoop);
			var carro = mapper.readValue(json,DadosCarro.class);
			listaCarros.add(carro);
		}

		listaCarros.stream()
				.sorted(Comparator.comparing(DadosCarro::anoModelo).reversed())
				.forEach(c-> System.out.println(
						c.valor() + " " + c.anoModelo() + " " + c.marca() + " " + c.modelo()));
	}
}

