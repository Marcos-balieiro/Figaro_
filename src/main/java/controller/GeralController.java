package controller;
import modelo.Assunto;
import modelo.Classe_Judicial;
import modelo.Entidade;
import repository.SeleniumRepositorio;
import DAO.DAOprocessos;
import modelo.Processo;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

public class GeralController {
    private SeleniumRepositorio alerquina = new SeleniumRepositorio();
    private BancoController coringa = new BancoController();
    private DAOprocessos proc = new DAOprocessos();

    public String data() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime hoje = LocalDateTime.now();
        String dataAtual = hoje.format(formatter);
        return dataAtual;
    }

    public String figaro(String CPF, String senha, String entity, String data_fin, String dataInicio) throws InterruptedException, AWTException {
        alerquina.login(CPF, senha);
        String janelapadrao = alerquina.automatização();
        Entidade entidade = coringa.dadosEntidade(entity);
        String classes = entidade.getId_classe_judicial();
        String nome = entidade.getNome();
        int test = 0;
        String x = "";
        Set<String> exceões = new HashSet<String>(Arrays.asList(
                "AGÊNCIA NACIONAL DE ENERGIA ELÉTRICA", "AGÊNCIA NACIONAL DO PETROLEO GAS NATURAL E BIOCOMBUSTIVEIS", "AGÊNCIA NACIONAL DE AVIAÇÃO CIVIL", "AGÊNCIA NACIONAL DE TELECOMUNICAÇÕES", "AGÊNCIA NACIONAL DE TRANSPORTES AQUAVIÁRIOS", "SUPERINTENDÊNCIA DE SEGUROS PRIVADOS", "AGÊNCIA NACIONAL DO CINEMA", "AGÊNCIA NACIONAL DE ÁGUAS", "DEPARTAMENTO NACIONAL DE OBRAS CONTRA AS SECAS", "SUPERINTENDÊNCIA NACIONAL DE PREVIDÊNCIA COMPLEMENTAR", "COMISSÃO DE VALORES MOBILIÁRIOS", "AGÊNCIA NACIONAL DE SAUDE SUPLEMENTAR", "COMISSÃO NACIONAL DE ENERGIA NUCLEAR", "AGÊNCIA ESPACIAL BRASILEIRA", "AGÊNCIA NACIONAL DE TRANSPORTES TERRESTRES", "AGÊNCIA NACIONAL DE VIGILÂNCIA SANITÁRIA"));
        if (exceões.contains(entidade.getNome())) {
            alerquina.entradadados(x, nome, test, data_fin,dataInicio);
            Vector processo = alerquina.pesquisaExceçoes(janelapadrao, nome);
            for (int contador = 0; contador < processo.size(); contador++) {
                Processo processo1 = new Processo();
                processo1.setProcessos(String.valueOf(processo.get(contador)));
                processo1.setNomeEntidade(nome);
                processo1.setDataEncontrada(data());
                proc.salvarprocessos(processo1);
            }

        } else {
            List<String> id_classes = new ArrayList<>(Arrays.asList(classes.split(",")));
            for (int i = 0; i < id_classes.size(); i++) {

                String pesquisaClasse = (id_classes.get(i));

                Classe_Judicial classe = coringa.dadosjudicial(pesquisaClasse);
                String judicialclass = classe.getNome();
                alerquina.entradadados(judicialclass, nome, test, data_fin, dataInicio);

                Vector processo = alerquina.pesquisa(janelapadrao, entidade.getNome());
                for (int contador = 0; contador < processo.size(); contador++) {
                    Processo processo1 = new Processo();
                    processo1.setProcessos(String.valueOf(processo.get(contador)));
                    processo1.setNomeEntidade(nome);
                    processo1.setDataEncontrada(data());
                    proc.salvarprocessos(processo1);

                }
            }
            if (nome.equals("DEPARTAMENTO NACIONAL DE INFRAESTRUTURA DE TRANSPORTE")) {
                String vazio = "";
                alerquina.entradadados(vazio, nome, test, data_fin, dataInicio);
                Vector processoex = alerquina.exceções2(janelapadrao, nome);
                for (int contador = 0; contador < processoex.size(); contador++) {
                    Processo processo1 = new Processo();
                    processo1.setProcessos(String.valueOf(processoex.get(contador)));
                    processo1.setNomeEntidade(nome);
                    processo1.setDataEncontrada(data());
                    proc.salvarprocessos(processo1);

                }
            }
            Set<String> agenmuni = new HashSet<String>(Arrays.asList(
                    "SUPERINTENDÊNCIA DA ZONA FRANCA DE MANAUS", "SUPERINTENDÊNCIA DO DESENVOLVIMENTO DO CENTRO-OESTE", "SUPERINTENDÊNCIA DO DESENVOLVIMENTO DA AMAZÔNIA", "SUPERINTENDÊNCIA DO DESENVOLVIMENTO DO NORDESTE", "INSTITUTO BRASILEIRO DE GEOGRAFIA E ESTATÍSTICA", "FUNDAÇÃO NACIONAL DE SAÚDE", "FUNDO NACIONAL DESENVOLVIMENTO DA EDUCACAO", "AGÊNCIA NACIONAL DE MINERAÇÃO"));
            if (agenmuni.contains(nome)) {
                String vazio = "";
                alerquina.entradadados(vazio, nome, test, data_fin, dataInicio);
                Vector processoex = alerquina.pesquisaMunicipio(janelapadrao, nome);
                for (int contador = 0; contador < processoex.size(); contador++) {
                    Processo processo1 = new Processo();
                    processo1.setProcessos(String.valueOf(processoex.get(contador)));
                    processo1.setNomeEntidade(nome);
                    processo1.setDataEncontrada(data());
                    proc.salvarprocessos(processo1);
                }
            }
        }
        String assuntos = entidade.getId_assunto();
        if (assuntos != null) {
            List<String> id_assuntos = new ArrayList<>(Arrays.asList(assuntos.split(",")));
            for (int i = 0; i < id_assuntos.size(); i++) {
                test = 24;
                String pesquisaassunto = (id_assuntos.get(i));
                Assunto assunto = coringa.dadosassunto(pesquisaassunto);
                String Subject = assunto.getNome();
                alerquina.entradadados(Subject, nome, test, data_fin, dataInicio);
                Set<String> opções = new HashSet<String>(Arrays.asList(
                        "INSTITUTO BRASILEIRO DO MEIO AMBIENTE E DOS RECURSOS NATURAIS RENOVAVEIS", "INSTITUTO CHICO MENDES DE CONSERVACAO DA BIODIVERSIDADE"
                ));
                if (opções.contains(nome)) {
                    Vector processo = alerquina.pesquisa2(janelapadrao, entidade.getNome());
                    for (int contador = 0; contador < processo.size(); contador++) {
                        Processo processo1 = new Processo();
                        processo1.setProcessos(String.valueOf(processo.get(contador)));
                        processo1.setNomeEntidade(nome);
                        processo1.setDataEncontrada(data());
                        proc.salvarprocessos(processo1);
                    }
                } else {
                    Vector processo = alerquina.pesquisa(janelapadrao, entidade.getNome());
                    for (int contador = 0; contador < processo.size(); contador++) {
                        Processo processo1 = new Processo();
                        processo1.setProcessos(String.valueOf(processo.get(contador)));

                        processo1.setNomeEntidade(nome);
                        processo1.setDataEncontrada(data());
                        proc.salvarprocessos(processo1);

                    }
                }

            }
        }
        alerquina.driver.close();
        return null;
    }
    public void figarotodos(String CPF, String senha, Vector<String> entity, String data_fin, String dataInicio) throws InterruptedException, AWTException {
        alerquina.login(CPF, senha);
        for (int j = 0; j < entity.size(); j++) {
            String janelapadrao = alerquina.automatização();
            String teste = String.valueOf(entity.get(j));
            Entidade entidade = coringa.dadosEntidade(teste);
            String classes = entidade.getId_classe_judicial();
            String nome = entidade.getNome();
            int test = 0;
            String x = "";
            Set<String> exceões = new HashSet<String>(Arrays.asList(
                    "AGÊNCIA NACIONAL DE ENERGIA ELÉTRICA", "AGÊNCIA NACIONAL DO PETROLEO GAS NATURAL E BIOCOMBUSTIVEIS", "AGÊNCIA NACIONAL DE AVIAÇÃO CIVIL", "AGÊNCIA NACIONAL DE TELECOMUNICAÇÕES", "AGÊNCIA NACIONAL DE TRANSPORTES AQUAVIÁRIOS", "SUPERINTENDÊNCIA DE SEGUROS PRIVADOS", "AGÊNCIA NACIONAL DO CINEMA", "AGÊNCIA NACIONAL DE ÁGUAS", "DEPARTAMENTO NACIONAL DE OBRAS CONTRA AS SECAS", "SUPERINTENDÊNCIA NACIONAL DE PREVIDÊNCIA COMPLEMENTAR", "COMISSÃO DE VALORES MOBILIÁRIOS", "AGÊNCIA NACIONAL DE SAUDE SUPLEMENTAR", "COMISSÃO NACIONAL DE ENERGIA NUCLEAR", "AGÊNCIA ESPACIAL BRASILEIRA", "AGÊNCIA NACIONAL DE TRANSPORTES TERRESTRES", "AGÊNCIA NACIONAL DE VIGILÂNCIA SANITÁRIA"));
            if (exceões.contains(entidade.getNome())) {
                alerquina.entradadados(x, nome, test, data_fin, dataInicio);
                Vector processo = alerquina.pesquisaExceçoes(janelapadrao,nome);
                for (int contador = 0; contador < processo.size(); contador++) {
                    Processo processo1 = new Processo();
                    processo1.setProcessos(String.valueOf(processo.get(contador)));
                    processo1.setNomeEntidade(nome);
                    processo1.setDataEncontrada(data());
                    proc.salvarprocessos(processo1);
                }
                System.out.println(entidade.getNome());
            } else {
                System.out.println(entidade.getNome());
                List<String> id_classes = new ArrayList<>(Arrays.asList(classes.split(",")));
                for (int i = 0; i < id_classes.size(); i++) {

                    String pesquisaClasse = (id_classes.get(i));

                    Classe_Judicial classe = coringa.dadosjudicial(pesquisaClasse);
                    String judicialclass = classe.getNome();
                    alerquina.entradadados(judicialclass, nome, test, data_fin, dataInicio);

                    Vector processo = alerquina.pesquisa(janelapadrao, entidade.getNome());
                    for (int contador = 0; contador < processo.size(); contador++) {
                        Processo processo1 = new Processo();
                        processo1.setProcessos(String.valueOf(processo.get(contador)));
                        processo1.setNomeEntidade(nome);
                        processo1.setDataEncontrada(data());
                        proc.salvarprocessos(processo1);
                    }

                }
                Set<String> agenmuni = new HashSet<String>(Arrays.asList(
                        "SUPERINTENDÊNCIA DA ZONA FRANCA DE MANAUS", "SUPERINTENDÊNCIA DO DESENVOLVIMENTO DO CENTRO-OESTE", "SUPERINTENDÊNCIA DO DESENVOLVIMENTO DA AMAZÔNIA", "SUPERINTENDÊNCIA DO DESENVOLVIMENTO DO NORDESTE", "INSTITUTO BRASILEIRO DE GEOGRAFIA E ESTATÍSTICA", "FUNDAÇÃO NACIONAL DE SAÚDE", "FUNDO NACIONAL DESENVOLVIMENTO DA EDUCACAO", "AGÊNCIA NACIONAL DE MINERAÇÃO"));
                if (agenmuni.contains(nome)) {
                    String vazio = "";
                    alerquina.entradadados(vazio, nome, test, data_fin, dataInicio);
                    Vector processoex = alerquina.pesquisaMunicipio(janelapadrao, nome);
                    for (int contador = 0; contador < processoex.size(); contador++) {
                        Processo processo1 = new Processo();
                        processo1.setProcessos(String.valueOf(processoex.get(contador)));
                        processo1.setNomeEntidade(nome);
                        processo1.setDataEncontrada(data());
                        proc.salvarprocessos(processo1);
                    }
                }
                if (nome == "DEPARTAMENTO NACIONAL DE INFRAESTRUTURA DE TRANSPORTE") {
                    String vazio = "";
                    alerquina.entradadados(vazio, nome, test, data_fin, dataInicio);
                    Vector processoex = alerquina.exceções2(janelapadrao,nome);
                    for (int contador = 0; contador < processoex.size(); contador++) {
                        Processo processo1 = new Processo();
                        processo1.setProcessos(String.valueOf(processoex.get(contador)));
                        processo1.setNomeEntidade(nome);
                        processo1.setDataEncontrada(data());
                        proc.salvarprocessos(processo1);
                    }
                } else {
                    String assuntos = entidade.getId_assunto();
                    if (assuntos != null) {
                        List<String> id_assuntos = new ArrayList<>(Arrays.asList(assuntos.split(",")));
                        for (int i = 0; i < id_assuntos.size(); i++) {
                            test = 24;
                            String pesquisaassunto = (id_assuntos.get(i));
                            Assunto assunto = coringa.dadosassunto(pesquisaassunto);
                            String Subject = assunto.getNome();
                            alerquina.entradadados(Subject, nome, test, data_fin, dataInicio);
                            Set<String> opções = new HashSet<String>(Arrays.asList(
                                    "INSTITUTO BRASILEIRO DO MEIO AMBIENTE E DOS RECURSOS NATURAIS RENOVAVEIS", "INSTITUTO CHICO MENDES DE CONSERVACAO DA BIODIVERSIDADE"
                            ));
                            if (opções.contains(nome)) {
                                Vector processo = alerquina.pesquisa2(janelapadrao, entidade.getNome());
                                for (int contador = 0; contador < processo.size(); contador++) {
                                    Processo processo1 = new Processo();
                                    processo1.setProcessos(String.valueOf(processo.get(contador)));
                                    processo1.setNomeEntidade(nome);
                                    processo1.setDataEncontrada(data());
                                    proc.salvarprocessos(processo1);
                                }
                            }
                                String xamblu = "";
                                Vector processo = alerquina.pesquisa(janelapadrao, xamblu);
                                for (int contador = 0; contador < processo.size(); contador++) {
                                    Processo processo1 = new Processo();
                                    processo1.setProcessos(String.valueOf(processo.get(contador)));
                                    processo1.setNomeEntidade(nome);
                                    processo1.setDataEncontrada(data());
                                    proc.salvarprocessos(processo1);
                                }


                            }
                        }

                    }
                }

            }
        alerquina.driver.close();
        }
    }
