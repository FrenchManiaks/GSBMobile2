JSONArray jsonArrayrv = new JSONArray(response);
                    JSONObject jsonObjectrdv = jsonArrayrv.getJSONObject(0);
                    if (jsonObjectrdv.has("id")) {
                        SingletonUser s1 = SingletonUser.getInstance();
                        ArrayList<String> idlist = new ArrayList<>();
                        ArrayList<String> DateList = new ArrayList<>();
                        ArrayList<String> NomList = new ArrayList<>();
                        ArrayList<String> PrenomList = new ArrayList<>();
                        ArrayList<String> TestList = new ArrayList<>();
                        ArrayList<String> RdvList = new ArrayList<>();

                        ArrayList <String> test = new ArrayList<>();

                        for (int i = 0; i < jsonArrayrv.length() ; i++){
                            JSONObject jsonObjectId = jsonArrayrv.getJSONObject(i);
//                            test.add("N° " + s1.getRdvId() + " Le " + s1.getRdvDate() + " Avec " + s1.getRdvNomPraticien());
                            idlist.add(jsonObjectId.getString("id"));
                            DateList.add(jsonObjectId.getString("date"));
                            s1.setRdvId(idlist);
                            s1.setRdvDate(DateList);


                            /**TEST
                             *
                             */

                            s1.setRdvInfo(jsonObjectrdv.getString("description"));
                                JSONObject jsonObjecttes = jsonArrayrv.getJSONObject(i);
                                TestList.add(jsonObjecttes.getString("praticien"));

                            JSONArray jsonArrayPraticien = new JSONArray(TestList);

                                for (int k = 0 ; k < jsonArrayPraticien.length(); k++){
                                    JSONObject jsonObject = new JSONObject(jsonArrayPraticien.getString(k));
                                    NomList.add(jsonObject.getString("nom"));
                                    PrenomList.add(jsonObject.getString("prenom"));

                                    RdvList.add(DateList + " Avec "+ NomList + " " + PrenomList);

                                    s1.setRdvPrenomPraticien(PrenomList);
                                    s1.setRdvNomPraticien(NomList);

                                    test.add("N°" + jsonObjectId.getString("id") + "_ Le " + jsonObjectId.getString("date") + " Avec " +
                                            jsonObject.getString("nom"));


                                }

                        }
