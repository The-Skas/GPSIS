/**
 * 
 */
package object;


/**
 * @author VJ
 *
 */
import java.util.Date;
import java.util.Set;

public class MedicalStaffMember extends StaffMember {
	
	protected Set<CalendarAppointment> appointments;
	//protected Set<Referral> referrals;
	//protected Set<Prescription> prescriptions;
	protected Set<Patient> patients;
	protected Set<CareProgramme> careProgrammes;
	protected Set<Speciality> specialities;
	
	// retrieve from database
	public MedicalStaffMember(int id, String u, byte[] p, String fN, String lN, boolean fT, Date sD, boolean oM, String r, int hA)
	{
		// TODO MedicalStaffMember:Constructor
		super(id, u, p, fN, lN, fT, sD, oM, r, hA);
		// retrieve appointments
		
		// retrieve referrals
		
		// retrieve prescriptions
		
		// retrieve patients 
		
		// retrieve care programmes
	}
	
	// insert into database
	public MedicalStaffMember(String u, String p, String fN, String lN, boolean fT, Date sD, boolean oM, String r, int hA)
	{
		super(u, p, fN, lN, fT, sD, oM, r, hA);
	}

	public boolean hasSpeciality(Speciality s)
	{
		return this.specialities.contains(s);
	}
	
	public boolean isAvailable(Date c)
	{
		return !this.unavailables.contains(c) && !this.appointments.contains(c) && !this.holidays.contains(c);
	}
	
	public Set<CalendarAppointment> getAppointments()
	{
		// TODO MedicalStaffMember.getAppointments
		return null;
	}
	
	/*public Set<Referral> getReferrals()
	{
		// TODO MedicalStaffMember.getReferrals
		return null;
	}*/
	
	public Set<Patient> getPatients()
	{
		// TODO MedicalStaffMember.getPatients
		return null;
	}
	
	/*public Set<Prescription> getPrescriptions()
	{
		// TODO MedicalStaffMember.getPrescriptions
		return null;
	}*/
	
	public Set<CareProgramme> getCareProgrammes()
	{
		// TODO MedicalStaffMember.getCareProgrammes
		return null;
	}
}
