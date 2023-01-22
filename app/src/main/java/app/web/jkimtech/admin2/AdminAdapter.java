package app.web.jkimtech.admin2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

public class AdminAdapter extends RecyclerView.Adapter<AdminAdapter.Holder> {
    private Context context;
    private List<Upload> uploadList;
    private ClickListener listener;
    private List<User> userData;
    private List<User> downloadUrls;

    public AdminAdapter(Context context, List<Upload> uploadList, ClickListener listener) {
        this.context = context;
        this.uploadList = uploadList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.applicant_data, parent, false);
        Holder holder = new Holder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onclick(uploadList.get(holder.getAdapterPosition()));
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Upload upload = uploadList.get(position);
        holder.statusView.setText(String.format("Status: %s", upload.getStatus()));
        holder.DateView.setText(upload.getAppDate());
        holder.IdView.setText(upload.getUploadId());
        holder.nameView.setText(String.format("Name: %s", upload.getName()));
        holder.emailView.setText(String.format("Email: %s", upload.getEmail()));
        holder.phoneView.setText(String.format("Phone: %s", upload.getPhone()));
        holder.adnoView.setText(String.format("Adm No: %s", upload.getAdmNo()));
        holder.courseView.setText(String.format("Course: %s", upload.getCourse()));
        holder.institutionView.setText(String.format("Name: %s", upload.getInstitution()));
        holder.institutionPhoneView.setText(String.format("Inst. No: %s", upload.getInstitutionPhoneNo()));
        holder.bankView.setText(String.format("Bank: %s", upload.getBankName()));
        holder.accountNameView.setText(String.format("Account: %s", upload.getBankAccountNo()));
        holder.branchView.setText(String.format("Branch: %s", upload.getBankBranch()));
        holder.districtView.setText(String.format("District: %s", upload.getDistrict()));
        holder.divisionView.setText(String.format("Division: %s", upload.getDivision()));
        holder.villageView.setText(String.format("Village: %s", upload.getVillage()));
        holder.wardView.setText(String.format("Ward: %s", upload.getWard()));
        holder.locationView.setText(String.format("Location: %s", upload.getLocation()));
        holder.subLocationView.setText(String.format("S.Location: %s", upload.getSubLocation()));
        holder.constituencyView.setText(String.format("Constituency: %s", upload.getConstituency()));
        holder.dobView.setText(String.format("DoB: %s", upload.getDate()));
        holder.yearView.setText(String.format("Year of study: %s", upload.getYearOfStudy()));
        Glide.with(context).load(uploadList.get(position).getDownloadUrls().get(0)).into(holder.idimageView);
        Glide.with(context).load(uploadList.get(position).getDownloadUrls().get(1)).into(holder.certimageView);
        Glide.with(context).load(uploadList.get(position).getDownloadUrls().get(2)).into(holder.feeimageView);
        Glide.with(context).load(uploadList.get(position).getDownloadUrls().get(3)).into(holder.reportimageView);

    }

    @Override
    public int getItemCount() {
        return uploadList.size();
    }

    public static class Holder extends RecyclerView.ViewHolder {
        MaterialTextView IdView, DateView, statusView, nameView, adnoView, courseView, emailView, phoneView, institutionView, institutionPhoneView, bankView, branchView, accountNameView,districtView, divisionView, locationView, wardView, constituencyView, subLocationView, villageView, dobView, yearView;
        MaterialCardView cardView;
        ImageView reportimageView, feeimageView, idimageView, certimageView;

        public Holder(@NonNull View itemView) {
            super(itemView);
            IdView = itemView.findViewById(R.id.applicationId);
            DateView = itemView.findViewById(R.id.applicationDate);
            statusView = itemView.findViewById(R.id.applicationStatus);
            nameView = itemView.findViewById(R.id.name);
            emailView = itemView.findViewById(R.id.email_address);
            phoneView = itemView.findViewById(R.id.phone_number);
            adnoView = itemView.findViewById(R.id.admission_number);
            courseView = itemView.findViewById(R.id.course);
            institutionView = itemView.findViewById(R.id.institution_details);
            institutionPhoneView = itemView.findViewById(R.id.institution_number);
            bankView = itemView.findViewById(R.id.bank_name);
            branchView = itemView.findViewById(R.id.bank_branch);
            accountNameView = itemView.findViewById(R.id.bank_account_number);
            districtView = itemView.findViewById(R.id.district);
            divisionView = itemView.findViewById(R.id.division);
            locationView = itemView.findViewById(R.id.location);
            wardView = itemView.findViewById(R.id.ward);
            constituencyView = itemView.findViewById(R.id.constituency);
            subLocationView = itemView.findViewById(R.id.sub_location);
            dobView = itemView.findViewById(R.id.dob);
            villageView = itemView.findViewById(R.id.village);
            cardView = itemView.findViewById(R.id.image_card);
            idimageView = itemView.findViewById(R.id.idImage);
            certimageView = itemView.findViewById(R.id.certImg);
            feeimageView = itemView.findViewById(R.id.feeImg);
            reportimageView = itemView.findViewById(R.id.reportImg);
            yearView = itemView.findViewById(R.id.year_of_study);
        }
    }
}